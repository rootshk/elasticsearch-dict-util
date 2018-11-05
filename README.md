# elasticsearch-dict-util
elasticsearch远程字典更新工具

## API
### elasticsearch ik远程扩展字典地址:
- 扩展字典: /ik-server/dict
- 停用词字典: /ik-server/stopwords

### elasticsearch ik远程扩展字典地址:

- 获取扩展字典: [GET] /api/dict
- 获取停用字典: [GET] /api/stopwords

- 添加扩展字典: [POST] /api/dict { "value": "具体的值" }
- 添加停用字典: [POST] /api/stopwords { "value": "具体的值" }

- 修改扩展字典: [PUT] /api/dict { "oldValue": "原本的值", "newValue": "原本的值" }
- 修改停用字典: [PUT] /api/stopwords { "oldValue": "原本的值", "newValue": "原本的值" }

- 删除扩展字典: [DELETE] /api/dict { "value": "具体的值" }
- 删除停用字典: [DELETE] /api/stopwords { "value": "具体的值" }

## 配置文件
- 详见application.yml
