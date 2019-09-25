package com.xvjia.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xvjia.cms.dao.ArticleMapper;
import com.xvjia.cms.domain.Article;
import com.xvjia.cms.domain.Comment;
import com.xvjia.cms.service.ArticleServcie;

/**
 * @author xvjia
 * 	时间2019年9月10日
 * 
 */
@Service
public class ArticleServiceImpl implements ArticleServcie{

	@Resource
	private ArticleMapper articleMapper;
	
	@Override
	public PageInfo<Article> selects(Article article, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> selects = articleMapper.selects(article);
		PageInfo<Article> pageInfo = new PageInfo<Article>(selects);
		return pageInfo;
	}

	@Override
	public int insertSelective(Article record) {
		return articleMapper.insertSelective(record);
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Article selectPre(Article article) {
		return articleMapper.selectPre(article);
	}

	@Override
	public Article selectNext(Article article) {
		return articleMapper.selectNext(article);
	}

	@Override
	public PageInfo selectByHit() {
		PageHelper.startPage(0, 10);
		List<Article> selects = articleMapper.selectByHit();
		PageInfo<Article> pageInfo = new PageInfo<Article>(selects);
		
		return pageInfo;
	}


	@Override
	public PageInfo selectArticleByCount() {
		PageHelper.startPage(0, 10);
		List<Article> selects = articleMapper.selectArticleByCount();
		PageInfo<Article> pageInfo = new PageInfo<Article>(selects);
		return pageInfo;
	}

	

}
