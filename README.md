# 中国节日App (ChineseHolidayApp)

![License](https://img.shields.io/badge/license-Apache--2.0-blue)
![Platform](https://img.shields.io/badge/platform-Android-green)
![Language](https://img.shields.io/badge/language-Kotlin-orange)

一款专注于中国传统节日和法定假日的Android应用，帮助用户了解、查询和管理中国各类节假日信息。

## 功能特点

- 🏮 **节日浏览**：查看中国传统节日、法定假日和民族节日的详细信息
- 📅 **日历视图**：通过日历形式直观查看节假日安排
- 🔍 **智能搜索**：快速查找特定节日信息
- 🌙 **农历/公历转换**：支持农历和公历日期的互相转换
- 🔔 **节日提醒**：设置重要节日的提醒功能
- 🌈 **个性化设置**：自定义应用主题和显示偏好

## 技术栈

- **架构模式**：MVVM (Model-View-ViewModel)
- **UI组件**：Android Jetpack (Navigation, ViewModel, LiveData)
- **数据存储**：Room数据库
- **依赖注入**：基础DI模式
- **异步处理**：Kotlin协程
- **日期处理**：ThreeTen-ABP库

## 项目结构

项目采用模块化结构设计，主要包含以下模块：

- **数据层**：管理节日数据的存储和访问
- **UI层**：提供用户交互界面
  - 首页模块
  - 节日列表模块
  - 节日详情模块
  - 日历模块
  - 搜索模块
  - 设置模块

详细结构请参考 [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md)

## 安装说明

### 环境要求

- Android Studio最新版本
- JDK 11或更高版本
- Android SDK (API级别21及以上)
- 至少16GB内存（推荐）

### 安装步骤

1. 克隆仓库到本地：
```bash
git clone https://github.com/sjqwert/ChineseHolidayApp.git
```

2. 使用Android Studio打开项目：
   - 启动Android Studio
   - 选择 File > Open
   - 导航到ChineseHolidayApp目录
   - 点击OK

3. 等待Gradle同步完成

4. 运行应用：
   - 点击工具栏上的"Run"按钮(绿色三角形)
   - 选择目标设备（模拟器或实体设备）

详细安装指南请参考 [ENVIRONMENT_SETUP.md](ENVIRONMENT_SETUP.md)

## 使用指南

### 主要功能

1. **浏览节日**：
   - 在首页查看近期节日
   - 点击节日卡片查看详细信息

2. **使用日历**：
   - 切换到日历标签页
   - 滑动浏览不同月份
   - 点击带有标记的日期查看节日信息

3. **搜索节日**：
   - 点击搜索图标
   - 输入关键词（如"春节"、"中秋"）
   - 从结果列表中选择节日

详细使用说明请参考 [HOW_TO_RUN.md](HOW_TO_RUN.md)

## 贡献指南

欢迎贡献代码、报告问题或提出新功能建议！

1. Fork本仓库
2. 创建您的特性分支 (`git checkout -b feature/amazing-feature`)
3. 提交您的更改 (`git commit -m '添加一些很棒的功能'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 开启一个Pull Request

## 许可证

本项目采用Apache 2.0许可证 - 详情请参阅 [LICENSE](LICENSE) 文件

## 联系方式

项目维护者 - [@sjqwert](https://github.com/sjqwert)

项目链接: [https://github.com/sjqwert/ChineseHolidayApp](https://github.com/sjqwert/ChineseHolidayApp) 