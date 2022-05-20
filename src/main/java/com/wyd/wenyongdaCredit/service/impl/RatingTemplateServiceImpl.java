package com.wyd.wenyongdaCredit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyd.wenyongdaCredit.mapper.CustomerMapper;
import com.wyd.wenyongdaCredit.mapper.RatingTemplateMapper;
import com.wyd.wenyongdaCredit.model.dto.CustomerDTO;
import com.wyd.wenyongdaCredit.model.dto.RatingTemplateDTO;
import com.wyd.wenyongdaCredit.model.entity.CustomerEntity;
import com.wyd.wenyongdaCredit.model.entity.RatingTemplateEntity;
import com.wyd.wenyongdaCredit.service.ICustomerService;
import com.wyd.wenyongdaCredit.service.IRatingTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingTemplateServiceImpl extends ServiceImpl<RatingTemplateMapper, RatingTemplateEntity> implements IRatingTemplateService {

    private static final Logger log = LoggerFactory.getLogger(RatingTemplateServiceImpl.class);

    @Override
    public PageInfo<RatingTemplateEntity> selectRatingTemplateWithPageCount(Integer pageNumber, Integer pageSize, RatingTemplateDTO ratingTemplateDTO) {
        if (pageNumber != 0 && pageSize != 0) {
            PageHelper.startPage(pageNumber, pageSize);
            List<RatingTemplateEntity> userInfoVOList = this.baseMapper.selectList(ratingTemplateQueryWrapper(ratingTemplateDTO));
            PageInfo<RatingTemplateEntity> userInfoVOPageInfo = new PageInfo<>(userInfoVOList);
            PageHelper.clearPage();
            return userInfoVOPageInfo;
        } else {
            return null;
        }
    }

    @Override
    public Integer addRatingTemplate(RatingTemplateDTO ratingTemplateDTO) {
        if (ObjectUtil.isNotNull(ratingTemplateDTO)) {
            RatingTemplateEntity ratingTemplate = new RatingTemplateEntity();
            BeanUtil.copyProperties(ratingTemplateDTO, ratingTemplate);
            return this.baseMapper.insert(ratingTemplate);
        } else {
            return 0;
        }
    }

    @Override
    public Integer updateRatingTemplate(RatingTemplateDTO ratingTemplateDTO) {
        if (ObjectUtil.isNotNull(ratingTemplateDTO)) {
            return this.baseMapper.update(new RatingTemplateEntity(), ratingTemplateUpdateWrapper(ratingTemplateDTO));
        } else {
            return 0;
        }
    }

    @Override
    public Integer deleteRatingTemplate(List<String> ids) {
        if (ObjectUtil.isNotNull(ids) && ids.size() > 0) {
            return this.baseMapper.deleteBatchIds(ids);
        } else {
            return 0;
        }
    }

    private QueryWrapper<RatingTemplateEntity> ratingTemplateQueryWrapper(RatingTemplateDTO ratingTemplateDTO) {
        if (ObjectUtil.isNotNull(ratingTemplateDTO)) {
            QueryWrapper<RatingTemplateEntity> queryWrapper = new QueryWrapper<>();
            if (ObjectUtil.isNotNull(ratingTemplateDTO.getId())) {
                queryWrapper.eq("id", ratingTemplateDTO.getId());
            }
            if (StrUtil.isNotBlank(ratingTemplateDTO.getTemplateName())) {
                queryWrapper.eq("template_name", ratingTemplateDTO.getTemplateName());
            }
            if (ObjectUtil.isNotNull(ratingTemplateDTO.getTemplateType())) {
                queryWrapper.eq("template_type", ratingTemplateDTO.getTemplateType());
            }
            return queryWrapper;
        }
        return null;
    }

    private UpdateWrapper<RatingTemplateEntity> ratingTemplateUpdateWrapper(RatingTemplateDTO ratingTemplateDTO) {
        if (ObjectUtil.isNotNull(ratingTemplateDTO)) {
            UpdateWrapper<RatingTemplateEntity> updateWrapper = new UpdateWrapper<>();
            if (StrUtil.isNotBlank(String.valueOf(ratingTemplateDTO.getId()))) {
                updateWrapper.eq("id", ratingTemplateDTO.getId());
            }
            if (StrUtil.isNotBlank(ratingTemplateDTO.getTemplateName())) {
                updateWrapper.set("template_name", ratingTemplateDTO.getTemplateName());
            }
            if (StrUtil.isNotBlank(ratingTemplateDTO.getTemplateTermType())) {
                updateWrapper.set("template_term_type", ratingTemplateDTO.getTemplateTermType());
            }
            if (ObjectUtil.isNotNull(ratingTemplateDTO.getTemplateType())) {
                updateWrapper.set("template_type", ratingTemplateDTO.getTemplateType());
            }
            if (ObjectUtil.isNotNull(ratingTemplateDTO.getFinancialAccounting())) {
                updateWrapper.set("financial_accounting", ratingTemplateDTO.getFinancialAccounting());
            }
            if (ObjectUtil.isNotNull(ratingTemplateDTO.getNonFinancialAccounting())) {
                updateWrapper.set("non_financial_accounting", ratingTemplateDTO.getNonFinancialAccounting());
            }
            if (ObjectUtil.isNotNull(ratingTemplateDTO.getFinancialNonFinancialScoreGap())) {
                updateWrapper.set("financial_non_financial_score_gap", ratingTemplateDTO.getFinancialNonFinancialScoreGap());
            }
            if (ObjectUtil.isNotNull(ratingTemplateDTO.getIsEnabled())) {
                updateWrapper.set("is_enabled", ratingTemplateDTO.getIsEnabled());
            }
            return updateWrapper;
        }
        return null;
    }


}
