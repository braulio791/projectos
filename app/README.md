# Quiz de Informática - Aplicativo Android

## Descrição
Aplicativo Android interativo com um questionário de 5 perguntas sobre informática. O usuário responde as perguntas e ao final recebe uma pontuação com feedback personalizado.

## Funcionalidades
- ✅ 5 perguntas sobre informatica
- ✅ Múltiplas escolhas (A, B, C, D)
- ✅ Feedback em tempo real
- ✅ Placar final com mensagem personalizada
- ✅ Interface simples e intuitiva

## Perguntas Incluídas
1. Qual é a linguagem de programação mais antiga ainda em uso?
2. O que significa HTML?
3. Qual é a unidade básica de armazenamento em um computador?
4. O que é uma API?
5. Qual empresa criou o Java?

## Arquitetura
- **MVVM**: Usando ViewModel do AndroidX
- **LiveData**: Para observar mudanças de estado
- **Kotlin**: Linguagem principal do projeto

## Estrutura do Projeto
```
app/
├── src/main/
│   ├── java/com/example/quizapp/
│   │   ├── MainActivity.kt          # Tela principal do quiz
│   │   ├── ResultadoActivity.kt     # Tela de resultados
│   │   ├── Pergunta.kt              # Modelo de dados
│   │   └── QuizViewModel.kt         # ViewModel com lógica
│   └── res/
│       ├── layout/
│       │   ├── activity_main.xml    # Layout do quiz
│       │   └── activity_resultado.xml # Layout de resultados
│       └── values/
│           └── strings.xml          # Strings do app
```

## Como Usar
1. Clone o repositório
2. Abra no Android Studio
3. Execute em um emulador ou dispositivo físico
4. Responda as 5 perguntas
5. Veja sua pontuação e feedback

## Requisitos
- Android SDK 24+
- Android Studio 2022.1+
- Kotlin 1.8+

## Autor
Criado em 2026
