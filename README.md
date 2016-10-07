
这是一个简单的RecyclerView下拉刷新控件，在RecyclerView上实现平常ListView下拉刷新的效果。

需求来源：RecyclerView出现后，众多的新特性让我们逐渐用它来取代ListView。
但是用过RecyclerView的人都知道，RecyclerView下拉刷新的方式于与ListView相差有点大，
虽然官方已经提供了SwipeRefreshLayout来支持下拉刷新，但是我们的设计稿还是停留在原来ListView刷新的样式上。
这个问题很尴尬。。

优点：无需修改RecyclerView

注：当时项目有这个需求，网上找了好久没找到，今天就自己写了一个，分享给有需要的人。


用法：

    build导入module：

      compile project(path: ':pullrefreshlib')

    xml布局:
       <com.qzsang.pullrefreshlib.PullRefreshLayout
        android:id="@+id/prl_refresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <android.support.v7.widget.RecyclerView
            android:id="@+id/rv_list"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </com.qzsang.pullrefreshlib.PullRefreshLayout>

    java代码：

      设置监听 PullRefreshLayout.setOnRefreshListener();
      刷新完成:PullRefreshLayout.finishRefresh();




效果如下：

![image](https://github.com/qzsang/PullRefreshDemo/blob/master/doc/pullrefresh.gif)

