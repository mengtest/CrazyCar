package com.tastsong.crazycar.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tastsong.crazycar.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tastsong.crazycar.mapper.VersionMapper;
import com.tastsong.crazycar.model.VersionModel;

@Service
public class VersionService {
    @Autowired
    private VersionMapper versionMapper;

    public List<VersionModel> getVersionList(){
        return versionMapper.getVersionList();
    }

    public boolean updateVersion(VersionModel versionModel){
        return versionMapper.updateVersion(versionModel) == 1;
    }

    public boolean isForcedUpdating(String version, String platform) {
        try {
            String rs = getVersionByPlatform(platform);
            String[] minVersionStr = rs.split("\\.");
            String[] curVersionStr = version.split("\\.");
            int minVersion = Util.getSum(minVersionStr);
            int curVersion = Util.getSum(curVersionStr);
            return (minVersion - curVersion) > 0;
        } catch (Exception e) {
            return true;
        }

    }

    private String getVersionByPlatform(String platform) {
        QueryWrapper<VersionModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VersionModel::getPlatform, platform);
        VersionModel versionModel = versionMapper.selectOne(queryWrapper,false);
        return versionModel.getVersion();
    }

    public String getURL(String platform) {
        String rs = getUtlByPlatform(platform);
        if (rs== null){
            return getUtlByPlatform("Defeat");
        } else{
            return rs;
        }
    }

    private String getUtlByPlatform(String platform) {
        QueryWrapper<VersionModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VersionModel::getPlatform, platform);
        VersionModel versionModel = versionMapper.selectOne(queryWrapper,false);
        return versionModel.getUrl();
    }
}
