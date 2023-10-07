在JavaScript中，将一组数据转化为树形结构的数据通常用于表示层次关系，例如展示目录、组织结构或评论线程等。有多种实现这一需求的方法，下面介绍两种常用的方案：

**方案一：使用递归**

这种方法的关键是找到数据中的父子关系，通过递归遍历数据并构建树形结构。以下是一个具体实现示例：


```javascript
function buildTree(data, parentId = null) {
    let tree = [];
    let item;
    for (let i = 0; i < data.length; i++) {
        item = data[i];
        if (item.parentId === parentId) {
            const children = buildTree(data, item.id);
            if (children.length) {
                item.children = children;
            }
            tree.push(item);
        }
    }
    return tree;
}

// 使用示例
const data = [
    {id: 1, parentId: null, name: 'Node1'},
    {id: 2, parentId: 1, name: 'Node1.1'},
    {id: 3, parentId: 1, name: 'Node1.2'},
    {id: 4, parentId: 2, name: 'Node1.1.1'},
    {id: 5, parentId: 2, name: 'Node1.1.2'},
];

console.log(JSON.stringify(buildTree(data), null, 2));
```
**方案二：使用路径枚举**

另一种方案是使用路径枚举，通过记录每个节点的完整路径来建立树形结构。这种方法的优点是相对直观，缺点是当数据量大时，性能可能较差。以下是具体实现示例：


```javascript
function buildTree(data) {
    const map = new Map();
    const tree = [];
    for (let item of data) {
        const path = item.parentId ? `${item.parentId}.${item.id}` : item.id;
        if (!map.has(path)) {
            map.set(path, {...item, children: []});
        } else {
            map.get(path).children.push(item);
        }
    }
    for (let item of map.values()) {
        if (item.children && item.children.length) {
            tree.push(item);
        }
    }
    return tree;
}

// 使用示例
const data = [
    {id: 1, parentId: null, name: 'Node1'},
    {id: 2, parentId: 1, name: 'Node1.1'},
    {id: 3, parentId: 1, name: 'Node1.2'},
    {id: 4, parentId: 2, name: 'Node1.1.1'},
    {id: 5, parentId: 2, name: 'Node1.1.2'},
];

console.log(JSON.stringify(buildTree(data), null, 2));
```
以上两种方案都可以根据具体的数据结构和需求进行调整和优化。

Java教程：https://blog.csdn.net/weixin_67276852?type=blog