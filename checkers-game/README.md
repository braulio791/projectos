# Jogo de Dama - Android

Um jogo de dama implementado em Kotlin para Android, com interface intuitiva e suporte para jogabilidade contra IA.

## Funcionalidades

- ✅ Tabuleiro de 8x8 com peças e damas
- ✅ Regras clássicas de dama
- ✅ Modo single-player (contra IA)
- ✅ Modo multiplayer local
- ✅ Interface touch-friendly
- ✅ Animações suaves

## Requisitos

- Android Studio Flamingo ou superior
- JDK 11 ou superior
- Android SDK 21+ (compilação: 34+)
- Kotlin 1.8+

## Estrutura do Projeto

```
checkers-game/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/com/braulio791/checkers/
│   │   │   │   ├── GameActivity.kt
│   │   │   │   ├── GameBoard.kt
│   │   │   │   ├── GameLogic.kt
│   │   │   │   ├── AI.kt
│   │   │   │   └── ...
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       ├── drawable/
│   │   │       └── values/
│   │   └── test/
│   ├── build.gradle.kts
│   └── ...
├── build.gradle.kts
└── settings.gradle.kts
```

## Como Rodar

1. Clone o repositório
2. Abra o projeto no Android Studio
3. Sincronize as dependências do Gradle
4. Conecte um dispositivo Android ou inicie um emulador
5. Pressione `Run` (Shift + F10)

## Desenvolvimento

### Branches
- `feature/checkers-game` - Desenvolvimento principal
- `feature/ai-logic` - Implementação da IA
- `feature/ui-design` - Interface do usuário

### Commits
Use commits descritivos:
```
git commit -m "feat: adiciona lógica do tabuleiro"
git commit -m "fix: corrige movimento das peças"
git commit -m "refactor: melhora código da IA"
```

## Contribuindo

1. Crie um branch para sua feature
2. Faça commits semânticos
3. Abra um Pull Request

## Licença

MIT