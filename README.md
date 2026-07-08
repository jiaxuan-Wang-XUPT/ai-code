# AI 零代码应用生成平台

> 基于 Spring Boot 3 + LangChain4j + LangGraph4j，用自然语言描述需求，AI 自动完成素材搜集、代码生成、质量检查、项目构建，一键部署为可访问的 Web 应用。

---

## ✨ 三大核心能力

### 🧠 智能代码生成
输入自然语言需求，AI 自动分析意图并选择最优生成策略，通过工具调用动态生成完整项目代码。**流式输出**让您实时观察 AI 的思考与执行过程。

### 🎨 可视化编辑
生成的应用实时预览，一键进入编辑模式。自由点选页面元素，并与 AI 对话快速修改样式、布局或功能，直到满意为止。

### 🚀 一键部署分享
将生成的应用一键部署至云端，自动截取封面图，获得可公开访问的链接，随时分享。同时支持完整项目源码下载。

---

## 🛠 技术亮点

- **AI 服务集成**：基于 LangChain4j 声明式 `AiService`，快速集成 DeepSeek、通义千问等多种大模型。
- **对话历史查询**：时间游标分页 + 复合索引，查询性能提升 **3 倍**。
- **AI 智能路由**：根据需求动态选择生成策略，平衡成本与效果。
- **工具调用防幻觉**：三重保障（@Tool + Prompt 优化 + AiService 配置），触发率达 **99.9%**。
- **Prompt 安全审查**：基于输入护轨过滤敏感词、防范注入攻击。
- **高性能保障**：多级缓存 + 分布式限流 + 异步处理 + 护轨重试。




## 📸 项目截图

## | 应用生成流式输出 |
<img width="1920" height="935" alt="8fcc0cded554991b8134354568485810" src="https://github.com/user-attachments/assets/3a3a82fb-62b5-4df1-816b-65f9a8d7a2a0" />

## | 可视化编辑模式 |
<img width="1920" height="937" alt="image" src="https://github.com/user-attachments/assets/bbf1c1dd-b84f-4add-ad64-893592dcb774" />


## | 一键部署与分享 |
<img width="1920" height="938" alt="image" src="https://github.com/user-attachments/assets/d0fc75d3-6889-421a-b6e0-3ab8b72ad1c6" />

---

## 🚀 快速开始

### 环境要求
- JDK 21+
- Maven 3.8+
- MySQL 8.0+
- Redis 6.0+

### 本地运行

```bash
git clone https://github.com/your-username/ai-app-generator.git
cd ai-app-generator
mvn clean package -DskipTests
java -jar target/ai-app-generator-1.0.0.jar
