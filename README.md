# BaseLibrary
抽取Android开发常用代码

---
**目前主要有以下几个功能**

|分类|用途|
|:--|:--|
| base | 几个UI的基类 |
| cache | `DiskLruCache` 使用DiskLruCacheHelper进行调用 |
| dialog | BaseDialog | 
| eventbus | EventBusManager | 
| io | --- | 
| net | okhttp封装，使用HttpUtils | 
| permission | `MPermission` | 
| util | 一些常用工具 | 
| view | BadgeView，ClearEditText，RedTipView红点 | 


**Gradle依赖：**

```
dependencies {
	compile 'com.github.qiwei0727:BaseLibrary:v1.3.0'
}
```
