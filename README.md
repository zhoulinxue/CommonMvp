# CommonMvp

### 1、有问题请 提交 isuue/(QQ:194093798) 谢谢大家 持续更新

### 2、为新手提供一个 可靠 可用的 mvp 框架结构

### commonMvp 能做什么？
 1、mvp 实现 model  view  presenter 业务和界面解耦

 2、整合 网络 请求

 3、简化网络调用流程

 4、整合状态栏和标题栏 实现沉浸式 状态栏

 5、Activity 、Fragment 中 使用  方法 一致 同步封装 方法

### 配合使用的框架
    1、okhttp 、retrofit2、rxJava 、rxandroid (okhttp 实现网络全套)

    2、immersionbar  实现沉浸式状态栏

    3、fastjson   实现json 解析

## 集成

```
allprojects {
    repositories {
        jcenter()
    }
}
```
```
dependencies {
 implementation 'org.zhx.common:uikits:1.1.4'
 }
```
## 非Androidx 项目 ：
 gradle.properties中 添加：
```
android.useAndroidX=true
android.enableJetifier=true
```
## 代码调用
 1、创建 Mvp  contact类  将 presenter 接口、 view 接口  和okHttp  serverApi  关联在一起  WeatherApi
 ```
public interface WeatherApi {
    //OkHttp  api server
    @GET("http://t.weather.sojson.com/api/weather/city/101030100")
    public CommonObservable<WeatherInfo> getTest();
    //mvp  view
    public interface view extends BaseMvpView {
        void onWeatherInfo(WeatherInfo info);
    }
    //mvp presenter
    public interface presenter {
        void getWeatherInfo();
    }
}
 ```

 2、 创建 WeatherPresenter  主要业务类  继承 BasePresenter<WeatherApi.view>  实现 WeatherApi.presenter
 ```
public class WeatherPresenter extends BasePresenter<WeatherApi.view> implements WeatherApi.presenter {
    public WeatherPresenter(WeatherApi.view view) {
        super(view);
    }

    @Override
    public void getWeatherInfo() {
        //链式调用流程
        manager.with(WeatherApi.class).getTest().excute(new ObjectNetRequstAdapter<WeatherInfo>(mView) {

            @Override
            protected void onResultData(WeatherInfo info) {
                mView.onWeatherInfo(info);
            }
        });
    }
}
 ```
### Activity 、Fragment 中 使用  方法 一致 同步封装 方法

 3、 activity 中使用  继承 MvpActivity<WeatherPresenter>   实现  WeatherApi.view
 ```
 public class MainActivity extends MvpActivity<WeatherPresenter> implements WeatherApi.view {
     private TextView mTextView;

     @Override
     public WeatherPresenter initPresenter() {
         //TODO  初始化  presenter
         return new WeatherPresenter(this);
     }

     @Override
     public int initLayout() {
         //TODO 设置布局
         return R.layout.activity_main;
     }

     @Override
     public void onLoadArgumentsData(Intent intent) {
         //TODO  获取传递过来的参数

     }

     @Override
     public void onLoadDataFromSavedInstanceState(Bundle savedInstanceState) {
         //TODO  从低内存 获取 参数  （如果 你 在 onSaveInstanceState(Bundle outState) 方法中保存了数据）
     }

     @Override
     protected void onSaveInstanceState(@NonNull Bundle outState) {
         super.onSaveInstanceState(outState);
         //TODO 保存 数据 以供 从低内存恢复 时 还原界面
     }

     @Override
     public void onCreatView() {
         //TODO  初始化 组件
         mTextView = findViewById(R.id.result_tv);
     }

     @Override
     public void onLoadContent() {
         //TODO 在这个位置 获取 网络 数据
         mPresenter.getWeatherInfo();
     }

     @Override
     public void onWeatherInfo(WeatherInfo info) {
         //TODO 天气信息 (mPresenter.getWeatherInfo()  接口回调)
         mTextView.setText(info.toString());
     }

 }
 ```
 4、 Fragment中使用  继承 MvpFragment<WeatherPresenter>   实现  WeatherApi.view
```
public class TestFragment extends MvpFragment<WeatherPresenter> implements WeatherApi.view {
    private TextView mTextView;

    @Override
    public WeatherPresenter initPresenter() {
        //TODO 初始化 天气 presenter
        return new WeatherPresenter(this);
    }

    @Override
    protected void onCreateView(View rootView) {
        //TODO 初始化 view findViewById
        mTextView = rootView.findViewById(R.id.result_tv);
    }

    @Override
    public int initLayout() {
        //TODO 初始化 布局文件
        return R.layout.activity_main;
    }

    @Override
    public void onLoadContent() {
        //TODO 加载网络数据 或者 设置 传递过来的参数
        mPresenter.getWeatherInfo();
    }

    @Override
    public void onWeatherInfo(WeatherInfo info) {
        //TODO 天气信息 (mPresenter.getWeatherInfo()  接口回调)
        mTextView.setText(info.toString());

    }
}
```


