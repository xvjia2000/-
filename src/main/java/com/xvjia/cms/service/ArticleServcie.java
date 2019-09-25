
package com.xvjia.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xvjia.cms.domain.Article;
import com.xvjia.cms.domain.Comment;
import com.xvjia.cms.domain.User;

/**
 * @author xvjia
 * 	时间2019年9月10日
 * 
 */
public interface ArticleServcie {

	PageInfo<Article> selects(Article article,int pageNum, int pageSize);
	
    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

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
	PageInfo selectByHit();
	
	/**
	 * @return
	 */
	PageInfo selectArticleByCount();
}
