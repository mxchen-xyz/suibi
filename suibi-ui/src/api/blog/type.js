import request from '@/utils/request'


export default {
  /**博客分类 POST 操作**/
  // 新增博客类型
  addType(data) {
    return request({
      url: '/blog/type',
      method: 'post',
      data: data
    })
  },
  /**博客分类 DELETE 操作**/
  //删除分类
  delType(typeId) {
    return request({
      url: '/blog/type/' + typeId,
      method: 'delete'
    })
  },
  /**博客分类 PUT 操作**/
  updateType(data) {
    return request({
      url: '/blog/type',
      method: 'put',
      data: data
    })
  },
  changeTypeStatus(typeId, typeStatus) {
    const data = {
      typeId,
      typeStatus
    }
    return request({
      url: '/blog/type/changeStatus',
      method: 'put',
      data: data
    })
  },
  /**博客分类 GET 操作**/
  // 查询博客类型列表
  listType(query) {
    return request({
      url: '/blog/type/list',
      method: 'get',
      params: query
    })
  },
  //根据id获取分类
  getType(typeId) {
    return request({
      url: '/blog/type/' + typeId,
      method: 'get'
    })
  },
  exportType(query) {
    return request({
      url: '/blog/type/export',
      method: 'get',
      params: query
    })
  }

}
