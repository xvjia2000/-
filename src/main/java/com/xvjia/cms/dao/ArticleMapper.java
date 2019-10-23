package com.xvjia.cms.dao;

import java.util.List;


import com.xvjia.cms.domain.Article;

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