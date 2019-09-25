package com.xvjia.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xvjia.cms.domain.Article;
import com.xvjia.cms.domain.Comment;

public interface ArticleMapper {
	
	
	List<Article> selects(Article article);
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);


	/**
	 * @param article
	 * @return
	 */
	Article selectPre(Article article);


	/**
	 * @param article
	 * @return
	 */
	Article selectNext(Article article);


	/**
	 * @return
	 */
	List<Article> selectByHit();


	/**
	 * @return
	 */
	List<Article> selectArticleByCount();

	int updatecomments(Article article);
	
	
}