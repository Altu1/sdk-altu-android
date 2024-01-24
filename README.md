# Zenvia NLU SDK
Esse repositório contêm a documentação do SDK além de cópais dos pacotes binários para Android.

# Guia de implementação
Esse é o SDK para a funcionalidade de comunicação via Chatbot.

O SDK foi desenvolvido em Kotlin e conta com suporte amigável a Java.

# Como instalar
Para utilizar o SDK deve-se seguir o passo-a-passo abaixo.

# 1º Passo - Requisitos e configuração

### Configurar Integração no Zenvia NLU
- Ter um usuário cadastrado no Zenvia NLU;
- Acessar a página [Zenvia NLU](https://www.altu.com.br/login) e fazer o login;
- Na sidebar ir até o ícone **Connect** e selecionar **Canais**;

![00_connect](https://user-images.githubusercontent.com/80075244/223219106-b8c8d335-42e5-40bc-9059-976940b4c353.png) || ![01_menu canais](https://user-images.githubusercontent.com/80075244/223219328-a543d4ee-6651-43ac-950c-188e81aaeb81.png)

- Na tela de canais, escolher a opção **APP** você será redirecionado para página de **Integração com APP**;

![02_tela de canais](https://user-images.githubusercontent.com/80075244/223219511-b9558b15-dbaf-4b1a-849c-3967ad835873.png)

- Em Integrações com APP, clicar no botão **+** no canto direito inferior, que abrirá o formulário **Nova integração** para preenchimento dos campos obrigatórios com *Nome* da integração, opções de seleção de *Assistente* e *Squads*, e clicar no botão adicionar após o preenchimento dos campos;
![03 adicionar nova integração](https://user-images.githubusercontent.com/80075244/223220878-c6b1b86f-8ad2-447c-98df-6213d8903e50.png) 
![04 renomear integração](https://user-images.githubusercontent.com/80075244/223220998-8460aada-ad11-4f17-995d-dbe20dced819.png)

- De volta na tela de Integrações com APP, deverá aparecer a integração configurada e ao clicar no botão de edição, será redirecionado para página de **Editar integração** onde deve conter os dados configurados no passo anterior, além dos campos contendo o *Segredo de autorização*, *Chave de criptografia* (ambos contendo os botões de *Exibir* e *Esconder*), *Tempo de inatividade* (tendo por padrão 1h, e podendo ser configurado em um mínimo de 10 minutos e no máximo 24h),  chekbox para *Notificação de push* e abaixo aparecerão as informações de Autenticação para verificação;
![05 editar integração](https://user-images.githubusercontent.com/80075244/223221178-ad60ca44-179c-48f8-9ea6-c66dc3c12271.png)
![5-1_editar integração](https://user-images.githubusercontent.com/80075244/223221825-00638d6e-ee67-4f5e-8d37-d6a4078bdb47.png)
![06 Tela de integração app](https://user-images.githubusercontent.com/80075244/223221262-a4fe18d7-379b-4751-8038-6a904d19fb35.png)

- Ao fazer qualquer alteração, clique no botão **Salvar** no topo da tela.
![5 3_salvar alterações](https://user-images.githubusercontent.com/80075244/223222649-d1239c26-2e16-45aa-b35b-2b4aebc5fa63.png)

### Configurar APP

Tendo um dispositivo Android para testar

- Após baixar o APP, deve-se acessar a tela de **Editar integração** do Zenvia NLU para copiar as informações de autenticação;
![06 Tela de integração app](https://user-images.githubusercontent.com/80075244/223222918-f879a206-e993-46ee-b710-938cdc3b1a87.png)
![07 exibir secret](https://user-images.githubusercontent.com/80075244/223222771-fece64c8-e441-4b82-a442-e72ad322f342.png)
![08 Secret e source](https://user-images.githubusercontent.com/80075244/223223293-7ba2a4b7-9f84-4ccc-a3fe-fd215a2ab999.png)

- Ao abrir o App, deve-se preencher os campos:  
  - SourceId;
  - Slug;
  - Secret;
  - Channel (repetir o SourceId);
  - Salvar histórico de conversa (selecionar Always ou Ongoing);
  - Ambiente do websocket (selecionar Dev, Staging, Prod ou Custom);
  - Ambiente do chat (selecionar Dev, Staging, Prod ou Custom); 
  
![09_app exemplo](https://user-images.githubusercontent.com/80075244/223223434-48313b20-47ce-45bc-8a6e-8d46109c371f.png)
  
- Após preencher toda a configuração, deve-se clicar no botão de chat no canto diteito inferior da tela e será direcionado para o chatbot.

![10_app preenchimento](https://user-images.githubusercontent.com/80075244/223223778-7d9ca979-e314-47d4-af1f-127298c25fec.png) 

![11_app bot](https://user-images.githubusercontent.com/80075244/223224327-f1b92a79-edeb-4658-aa61-cffaf7fc2e6d.png)


### Uso do bot
- Esse App foi desenvolvido para troca de mensagens do tipo texto entre o usuário e o bot, os demais componentes suportados pelo Zenvia NLU serão desenvolvidos futuramente.

# 2º Passo - Importe o SDK da D1 para o seu projeto Android.

   Para usar o SDK deve-se importar no gradle do aplicativo:

       implementation 'cx.d1:altusdk:${Version}'

   Pode-se usar manualmente também, exemplo: https://www.androidbugfix.com/2021/12/how-to-import-aar-module-on-android.html

   - Baixar o diretório aar.
   - Adicionando ao seu projeto.
   - Configurando gradle para usar o aar.

# 3º Passo - Inicialize o SDK no Application

O ponto mais adequado e recomendado para configurar o SDK é na classe Application do seu aplicativo.

Caso ainda não tenha definido uma classe Application dedicada, pode-se iniciar o SDK na Activity principal, mas é altamente recomendável usar no Application.

Adicione no método onCreate do Application o código:

Kotlin

       D1AltuSdk.init(this)
       
       val config = D1AltuSdkConfig
            .setSlug("valor ha informar pela d1")
            .setSecret("valor ha informar pela d1")
            .setConversationHistoryType(ConversationHistoryType.ALWAYS)
            .setWebSocketEnvironment(D1AltuSdkWebSocketEnvironment.DEV)
            .setEnvironment(D1AltuSdkEnvironment.DEV)

       D1AltuSdk.setup(config)
Java

    D1AltuSdk.INSTANCE.init(this);

    D1AltuSdkConfig config = D1AltuSdkConfig.INSTANCE
            .setSlug("valor ha informar pela d1")
            .setSecret("valor ha informar pela d1")
            .setConversationHistoryType(ConversationHistoryType.ALWAYS)
            .setWebSocketEnvironment(D1AltuSdkWebSocketEnvironment.DEV)
            .setEnvironment(D1AltuSdkEnvironment.DEV);

    D1AltuSdk.INSTANCE.setup(config);

# 4º Passo - Abra uma conexão

Para abrir conexão com o chat e apresentar a tela para o cliente

    D1AltuSdk.openChat(
        widgetIdentifier: String,
        sourceId: String,
        data: HashMap<String, String>? = null,
        extraHash: String? = null
    ) 

# Documentação / API do SDK
Segue um detalhamento dos objetos e métodos do SDK

D1AltuSdkConfig
 - Objeto responsável por definir as configurações usadas no SDK. Só pode haver uma configuração ativa por vez.

## Criando instância
O D1AltuSdkConfig implementa o padrão _builder_ para criar objetos de configuração.

        val config = D1AltuSdkConfig
            .setSlug("valor ha informar pela d1")
            .setSecret("valor ha informar pela d1")
            .setConversationHistoryType(ConversationHistoryType.ALWAYS)
            .setAssistantEnvironment(D1AltuAssistantEnvironment.HOMOL)
            .setWebSocketEnvironment(D1AltuSdkWebSocketEnvironment.DEV)
            .setEnvironment(D1AltuSdkEnvironment.DEV)

## Métodos disponíveis no objeto D1AltuSdkConfig

### setSlug()
Configura a identificação do cliente.

Parâmetros:
- slug: String - **Obrigatório**

Exemplo:
```kotlin
D1AltuSdkConfig.setSlug("zenvia")
```
---
### setSecret()
Configura a chave de acesso fornecida pelo canal App.

Parâmetros:
- secret: String - **Obrigatório**

Exemplo:
```kotlin
D1AltuSdkConfig.setSecret("a1b2c3d4e5f6g7")
```
---
### setConversationHistoryType()
Define o comportamento para histórico de conversas, podendo ser configurado como **ALWAYS** ou **ONGOING**.

- **ALWAYS**: Irá armazenar o histórico de mensagens enviadas no atendimento que foi iniciado com essa opção configurada no tipo de histórico.
- **ONGOING**: Não irá armazenar o histórico de mensagens enviadas no atendimento que foi iniciado com essa opção configurada no tipo de histórico. Quando iniciado um novo atendimento, a tela de chat não terá mensagens anteriores **(a menos que algum atendimento anterior tenha sido iniciado com a configuração ALWAYS)**.

Parâmetros:
- type: ConversationHistoryType - **Obrigatório**

Exemplo:
```kotlin
D1AltuSdkConfig.setConversationHistoryType(ConversationHistoryType.ALWAYS)
// ou
D1AltuSdkConfig.setConversationHistoryType(ConversationHistoryType.ONGOING)
```
---
### setAssistantEnvironment()
Define qual ambiente do assistente que será utilizado, podendo ser escolhido entre a versão de **desenvolvimento**, **homologação** e **publicada**.

Parâmetros:
- environment: D1AltuAssistantEnvironment - **Obrigatório**

Exemplo:
```kotlin
D1AltuSdkConfig.setAssistantEnvironment(D1AltuAssistantEnvironment.DEV)
// ou
D1AltuSdkConfig.setAssistantEnvironment(D1AltuAssistantEnvironment.HOMOL)
// ou
D1AltuSdkConfig.setAssistantEnvironment(D1AltuAssistantEnvironment.PUBLISHED)
```
---
### setWebSocketEnvironment()
Define o ambiente que será usado do WebSocket.

Parâmetros:
- webSocketEnvironment: D1AltuSdkWebSocketEnvironment - **Obrigatório**

Exemplo:
```kotlin
D1AltuSdkConfig.setWebSocketEnvironment(D1AltuSdkWebSocketEnvironment.DEV)
```
---
### setEnvironment()
Define o ambiente que será usado.

Parâmetros:
- environment: D1AltuSdkEnvironment - **Obrigatório**

Exemplo:
```kotlin
D1AltuSdkConfig.setEnvironment(D1AltuSdkEnvironment.DEV)
```
---
## Métodos disponiveis do object D1AltuSdk


Inicializador do SDK, deve ser configurado na classe Application do aplicativo:

    fun init(applicationContext: Context)
    
Configurador do SDK, utilizado como exemplo para definir os ambientes de desenvolvimento:

    fun setup(config: D1AltuSdkConfig)
    
 Observer de interação de notificação da plataforma ALTU:

    D1AltuSdk.setChatNotificationListener(object : ChatNotificationListener {
            override fun onChatNotificationReceived(): Boolean {
                return if (D1AltuSdk.hasNotificationData()) {
                    D1AltuSdk.consumeNotificationData()?.let(::openChat) ?: false
                } else {
                    false
                }
            }
        })
        
    - true -> Informa para o SDK não seguir com o tratamento default. O tratamento fica a cargo do desenvolvedor. Como por exemplo abrir uma determinada activity em específico.    
    
    - false -> O SDK segue com seu tratamento default como por exemplo abrindo a activity principal do aplicativo.
    
Utilizado para iniciar uma conversação via chat:

    fun openChat(widgetIdentifier: String,sourceId: String,data: HashMap<String, String>? = null,extraHash: String? = null)
    
Retorna os dados da notificação de chat:

    fun getNotificationData(): String
    
Indica se existe dados de notificação disponivel:

    fun hasNotificationData(): Boolean
    
Retorna os dados de notificação e limpa os dados dessa notificação:

    fun consumeNotificationData(): String?
    
# Definindo estilo de apresentação do chat

    <style name="altuStyle">
         <item name="altu_main_color">@color/altu_main_color</item>
         <item name="altu_secondary_color">@color/altu_secondary_color</item>
         <item name="altu_main_text_color">@color/altu_main_text_color</item>
         <item name="altu_secondary_text_color">@color/altu_secondary_text_color</item>
         <item name="altu_background_color">@color/altu_background_color</item>
         <item name="altu_avatar">@drawable/avatar_chat_boot</item>
         <item name="altu_chat_avatar">@drawable/avatar_chat_boot</item>
         <item name="altu_chat_title">@string/chat_title_name</item>
    </style>
    
# Integração com o D1PushSdk

Para habilitar a integração com push notifications basta inicializa na classe Application do seu projeto o SDK de Push da seguinte forma: 
      
      D1PushSdk.setup(this, D1PushSdkConfig)

# Estilização personalizada
Caso haja a necessidade de alterar as cores da tela de chat, é possível definir alguns parâmetros de estilização para obter o resultado desejado.

Declare o estilo abaixo no arquivo `styles.xml`:
```xml
<style name="altuStyle">
    <item name="znlu_primary_color">@color/znlu_primary_color</item>  
	<item name="znlu_on_primary_color">@color/znlu_on_primary_color</item>  
	<item name="znlu_secondary_color">@color/znlu_secondary_color</item>  
	<item name="znlu_on_secondary_color">@color/znlu_on_secondary_color</item>  
	<item name="znlu_background">@color/znlu_background</item>  
	<item name="znlu_medium">@color/znlu_medium</item>  
	<item name="znlu_dark">@color/znlu_dark</item>
</style>
```


# Problemas comuns
    TBD
