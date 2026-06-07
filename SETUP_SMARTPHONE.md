# Setup e Execução no Smartphone

## ⚙️ Pré-requisitos

### No seu Computador:
1. **Android Studio** (versão 2022.1 ou superior)
   - Download: https://developer.android.com/studio

2. **Java Development Kit (JDK)** versão 11 ou superior
   - Já vem com Android Studio

3. **Git** instalado
   - Download: https://git-scm.com/

### No seu Smartphone:
- Android 7.0 ou superior (SDK 24+)
- USB 2.0 ou superior para conectar ao PC
- Modo USB Debugging ativado

---

## 📥 Passo 1: Clonar o Repositório

Abra o terminal/prompt de comando e execute:

```bash
git clone https://github.com/braulio791/projectos.git
cd projectos
git checkout app-questionario-android
```

---

## 🚀 Passo 2: Abrir no Android Studio

1. Abra o **Android Studio**
2. Clique em **"Open"** (ou File → Open)
3. Navegue até a pasta do projeto clonado
4. Selecione a pasta `projectos` e clique em **"OK"**
5. Aguarde o projeto carregar (pode levar alguns minutos)

---

## 📱 Passo 3: Habilitar USB Debugging no Smartphone

### Para Android 11 ou superior:
1. Vá para **Configurações**
2. Vá para **Sobre o Telefone**
3. Toque em **Número de Compilação** 7 vezes
4. Volte para **Configurações**
5. Vá para **Sistema** → **Opções de Desenvolvedor**
6. Ative **Depuração USB**
7. Confirme a solicitação de permissão

### Para Android 10 ou inferior:
1. Vá para **Configurações**
2. Vá para **Sobre o Telefone**
3. Toque em **Número de Compilação** 7 vezes
4. Volte para **Configurações**
5. Vá para **Opções de Desenvolvedor**
6. Ative **Depuração USB**

---

## 🔌 Passo 4: Conectar o Smartphone ao PC

1. Conecte o smartphone ao PC via **cabo USB**
2. No smartphone, escolha **Transferência de Arquivo** ou **Permitir Depuração USB**
3. No Android Studio, aguarde reconhecer o dispositivo

---

## ▶️ Passo 5: Executar o App

### Opção 1: Via Android Studio (Recomendado)

1. Clique no botão **Play** (▶️) verde na barra de ferramentas
2. Ou vá para **Run** → **Run 'app'**
3. Escolha seu smartphone na lista de dispositivos
4. Clique em **OK**
5. Aguarde a compilação e instalação (cerca de 1-2 minutos)

### Opção 2: Criar um APK para Instalar Manualmente

Se preferir instalar sem Android Studio:

1. Em Android Studio, vá para **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
2. Aguarde o build terminar
3. Clique em **Locate** para abrir a pasta
4. Encontre o arquivo `app-debug.apk`
5. Copie para uma pasta compartilhada ou envie por email
6. No smartphone, transfira o APK e execute para instalar

---

## 🎮 Passo 6: Usar o App

1. Abra o app "Quiz de Informática" no smartphone
2. Leia a pergunta atentamente
3. Selecione uma resposta (A, B, C ou D)
4. Clique em **"Próximo"** para ir para a próxima pergunta
5. Após responder todas as 5 perguntas, clique em **"Finalizar"**
6. Veja sua pontuação e feedback personalizado

---

## 🐛 Resolução de Problemas

### Erro: "Dispositivo não reconhecido"
- Tente usar um cabo USB diferente
- Instale os drivers USB do seu smartphone
- Reinicie o Android Studio

### Erro: "Build Failed"
- Vá para **File** → **Invalidate Caches** → **Invalidate and Restart**
- Clique em **Build** → **Clean Project**
- Clique em **Build** → **Rebuild Project**

### App não abre no smartphone
- Verifique se o Android é versão 7.0 ou superior
- Desinstale e instale novamente

---

## 📞 Suporte

Se tiver dúvidas, consulte:
- Documentação oficial: https://developer.android.com/
- Issues do repositório: https://github.com/braulio791/projectos/issues

Bom divertimento! 🎉
