<script src="underscore.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/lodash.js/4.17.21/lodash.core.js"></script>

// 产品树集合
let prodTreeList = [];
// 产品实例对象【objectId，ProdList】
let objIdProdMap = _.reduce(prodTreeList, (prodAcc, prod) => {
    prodAcc[prod.objectId] = prodAcc[prod.objectId] || [];
    prodAcc[prod.objectId] = prod;
    return prodAcc;
}, {});

// 产品实例对象【parentObjId，ProdList】
let parentObjIdProdMap = _.reduce(prodTreeList, (prodAcc, prod) => {
    prodAcc[prod.parentObjId] = prodAcc[prod.parentObjId] || [];
    prodAcc[prod.parentObjId] = prod;
    return prodAcc;
}, {});

// 获取顶层包
let topPackageList = prodTreeList.filter(_.isEmpty(objIdProdMap[prod.parentObjId]));

// 标识每个产品归属的顶层包?


// <顶层包实例,<产品实例，产品实例下所有子产品集合>>
// _.reduce(topPackageList,)


// 获取顶层包下的包结构，并添加层级标识
// 添加产品层级标识
prodTreeList.filter({objectId: "", prodId: ""}).forEach(topProd => {
    topProd.prodLevle = 1;
    let prodQueue = [];

    // 第二层产品加入队列 && 添加层级标识
    let nextProdPkgList = parentObjIdProdMap[topProd.objectId];
    if (!_.isEmpty(nextProdPkgList)) {
        prodQueue.concat(nextProdPkgList);
        _.forEach(nextProdPkgList, nextProd => nextProd.prodLevle = topProd.prodLevle + 1);
    }

    // 循环将当前产品的子产品加入队列，并添加层级标识
    while (prodQueue.length) {
        let cruProd = prodQueue.pop();
        let nextProdPkgList = parentObjIdProdMap[cruProd.objectId];
        if (_.isEmpty(nextProdPkgList)) {
            continue;
        }
        prodQueue.concat(nextProdPkgList);
        _.forEach(nextProdPkgList, prod => prod.prodLevle = cruProd.prodLevle + 1);
    }
});

// 根据组合产品自定义表过滤包下产品
let showProdList = [];
_.forEach(prodTreeList, prod => {
    if (prod.prodLevle === '1' && prod.prodLevle === '2') {
        showProdList.push(prod);
    }
    if (prod.prodLevle === '3' && _.includes([], prod.prodId)) {
        showProdList.push(prod);
    }
});

// 根据默认选中字典添加默认选中


// 产品树添加层级标识
prodTreeList.filter({objectId: "", prodId: ""}).forEach(prod => {
    // 设置产品层级标识
    function setProdLevel(topProd, firstProdLevle = '1') {
        topProd.prodLevle = firstProdLevle;
        // 产品队列
        let prodQueue = [];
        // 当前产品是否还有下层包
        let nextProdPkgList = parentObjIdProdMap[topProd.objectId];
        if (!_.isEmpty(nextProdPkgList)) {
            // 将下层包产品加入队列,设置产品层级标识
            prodQueue.concat(nextProdPkgList);
            _.forEach(nextProdPkgList, nextProd => nextProd.prodLevle = topProd.prodLevle + 1);
        }
        while (prodQueue.length) {
            // 从队列获取产品
            let cruProd = prodQueue.pop();
            let nextProdPkgList = parentObjIdProdMap[cruProd.objectId];
            if (_.isEmpty(nextProdPkgList)) {
                continue;
            }
            prodQueue.concat(nextProdPkgList);
            _.forEach(nextProdPkgList, prod => prod.prodLevle = cruProd.prodLevle + 1);
        }
    }
    setProdLevel(prod);
});



const bfs = (tree, ope) => {
    debugger;
    const walk = (tree, depth = 1) => {
        const queue = []
        ope(tree.name, depth)
        if (tree.children) {
            queue.push({
                nodes: tree.children,
                depth: depth + 1
            })
        }
        while (queue.length) {
            const item = queue.pop()
            item.nodes && item.nodes.forEach(node => {
                ope(node.name, item.depth)
                if (node.children) {
                    queue.push({
                        nodes: node.children,
                        depth: item.depth + 1
                    })
                }
            })
        }
    }
    walk(tree)
};

const data = {
    name: 'all',
    children: [{
        name: '图片',
        children: [{name: 'image1.jpg'}, {
            name: '风景',
            children: [{name: 'guilin.jpg'}, {name: 'hainan.jpg'}]
        }, {name: 'image2.jpg'}],
    }, {name: '视频', children: [{name: 'video1.mp4'}, {name: 'video2.mp4'}]}, {
        name: '文档',
        children: [{name: 'document1.doc'}, {
            name: '小说',
            children: [{name: 'novel.txt'}, {name: 'novel2.txt'}]
        }, {name: 'document2.doc'}]
    }]
}

bfs(data, (name, depth) => {
    let pre = '';
    for (let i = 0; i < depth; i++) {
        pre += '--'
    }
    console.log(pre + name)
})


const test = (varList, ope) => {
    ope(varList.name);
}



