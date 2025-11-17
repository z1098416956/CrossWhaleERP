package com.cross.whale.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.CitiesDao;
import com.cross.whale.dao.ProvincesDao;
import com.cross.whale.entity.CitiesDO;
import com.cross.whale.entity.ProvincesDO;
import com.cross.whale.res.ProvincesVO;
import com.cross.whale.service.ProvincesService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProvincesServiceImpl implements ProvincesService {

    @Resource
    private ProvincesDao provincesDao;

    @Resource
    private CitiesDao citiesDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取城市信息
     *
     * @return
     */
    @Override
    public CommonResult<List<ProvincesVO>> getProvincesInfo() {
        List<ProvincesVO> res = new ArrayList<>();
        Object o = redisTemplate.opsForValue().get("erp:provinces:list");
        if (o == null){
            List<ProvincesDO> list = provincesDao.queryProvincesList();
            if (list == null || list.isEmpty()){
                return CommonResult.error(SystemErrorCodeConstants.PROVINCES_IS_NULL);
            }
            List<CitiesDO> citiesDOS = citiesDao.queryCitiesList();
            Map<String, List<CitiesDO>> collect = citiesDOS.stream().collect(Collectors.groupingBy(CitiesDO::getProvinceId));
            for (ProvincesDO provincesDO : list){
                ProvincesVO vo = new ProvincesVO();
                vo.setPId("0");
                vo.setId(provincesDO.getProvinceId());
                vo.setName(provincesDO.getProvince());
                List<CitiesDO> doList = collect.get(provincesDO.getProvinceId());
                if (doList != null && !doList.isEmpty()){
                    List<ProvincesVO> children = new ArrayList<>();
                    for (CitiesDO citiesDO : doList){
                        ProvincesVO provincesVO = new ProvincesVO();
                        provincesVO.setId(citiesDO.getCityId());
                        provincesVO.setName(citiesDO.getCity());
                        provincesVO.setPId(provincesDO.getProvinceId());
                        children.add(provincesVO);
                    }
                    vo.setChildren(children);
                }
                res.add(vo);
            }
            String json = JSONObject.toJSONString(res);
            redisTemplate.opsForValue().set("erp:provinces:list",json);
        }else {
            res = JSON.parseArray(o.toString(), ProvincesVO.class);
        }

        return CommonResult.success(res);
    }
}
