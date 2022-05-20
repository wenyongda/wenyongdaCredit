package com.wyd.wenyongdaCredit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wyd.wenyongdaCredit.model.dto.RatingTemplateDTO;
import com.wyd.wenyongdaCredit.model.entity.CustomerEntity;
import com.wyd.wenyongdaCredit.model.entity.RatingTemplateEntity;

import java.util.List;

public interface IRatingTemplateService extends IService<RatingTemplateEntity> {

    PageInfo<RatingTemplateEntity> selectRatingTemplateWithPageCount(Integer pageNumber, Integer pageSize, RatingTemplateDTO ratingTemplateDTO);

    Integer addRatingTemplate(RatingTemplateDTO ratingTemplateDTO);

    Integer updateRatingTemplate(RatingTemplateDTO ratingTemplateDTO);

    Integer deleteRatingTemplate(List<String> ids);
}
