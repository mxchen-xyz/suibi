import request from '@/utils/request'

export default {
  /**增**/
  //创建博客
  addArticle(data) {
    return request({
      url: '/blog/article',
      method: 'post',
      data: data
    })
  },
  /**删**/
  //删除博客
  delArticle(articleId) {
    return request({
      url: '/blog/article/' + articleId,
      method: 'delete'
    })
  },
  /**改**/
  //修改博客
  updateArticle(data) {
    return request({
      url: '/blog/article',
      method: 'put',
      data: data
    })
  },
  /**查**/
  // 查询博客文章列表
  listArticle(query) {
    return request({
      url: '/blog/article/list',
      method: 'get',
      params: query
    })
  },

  getArticle(articleId) {
    return request({
      url: '/blog/article/' + articleId,
      method: 'get'
    })
  },
  exportArticle(query) {
    return request({
      url: '/blog/article/export',
      method: 'get',
      params: query
    })
  }
}

