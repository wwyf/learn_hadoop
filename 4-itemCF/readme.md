# 基于物品的推荐算法-hadoop实现

hadoop实现

主要参考慕课网上的一个教程：https://www.imooc.com/learn/890

同时感谢yb编写了易用的makefile，解决了编译java各种依赖的老大难问题以及编译后二进制文件的存放问题。

该程序跑在了我们自己在宿舍建立的一个具有3台笔记本的小小的集群中，训练的数据集来自https://grouplens.org/datasets/movielens/

该数据集总共有20000264行数据，一开始我们拿完整的数据集跑，发现jvm各种内存溢出


![](https://lh3.googleusercontent.com/-M5XHQ4SMLoc/WzeUHFn6U4I/AAAAAAAAIsM/jzKdEyaoo8EbmtxGTQnmY83GW_HwJUz9gCHMYCw/s0/MobaXterm_Personal_10.6_2018-06-30_22-30-51.png)

后来我们通过`head -300000 dataset.csv > new-dataset.csv`提取了数据集的前300000行数据进行训练，中间解决了一些类型不匹配的问题（应该是double但是用了int），重新训练了几次，睡了一觉起床发现还没训练完，不管了，先写一下readme.md再说。

![](https://lh3.googleusercontent.com/-WL-SmfMyaNQ/Wzgt-ne2oMI/AAAAAAAAIts/VakBY88ETWwpqjnAd-iPvsOPvUZr-n9-gCHMYCw/s0/Snipaste_2018-07-01_09-27-20.png)

坐等训练完毕:smile: