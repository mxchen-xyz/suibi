import request from '@/utils/request'

export default {
  //查询标签列表
  listLabel(query) {
    return request({
      url: '/blog/label/list',
      method: 'get',
      params: query
    })
  },
  // 根据id查询标签
  getLabel(articleId) {
    return request({
      url: '/blog/label/' + articleId,
      method: 'get'
    })
  },

// 新增标签
  addLabel(data) {
    return request({
      url: '/blog/label',
      method: 'post',
      data: data
    })
  },

// 修改标签
  updateLabel(data) {
    return request({
      url: '/blog/label',
      method: 'put',
      data: data
    })
  },
  changeLabelStatus(labelId, labelIdStatus) {
    const data = {
      labelId,
      labelIdStatus
    }
    return request({
      url: '/blog/label/changeStatus',
      method: 'put',
      data: data
    })
  },

// 删除标签
  delLabel(articleId) {
    return request({
      url: '/blog/label/' + articleId,
      method: 'delete'
    })
  },
}

