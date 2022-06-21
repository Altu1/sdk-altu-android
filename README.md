# D1 Smarkio ALTU SDK
Esse repositório contêm a documentação do SDK além de cópias dos pacotes binários para Android.

# Guia de implementação
Esse é o SDK para a funcionalidade de comunicação via Chatbot.

O SDK foi desenvolvido em Kotlin e conta com suporte amigável a Java.

# Como instalar
Para utilizar o SDK deve-se seguir o passo-a-passo abaixo.

# 1º Passo - Requisitos

   - Conta na D1
   - Um dispositivo Android para testar

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
 - object responsável por definir as configurações usadas no SDK. Só pode haver uma configuração ativa por vez.

Criando instância
 - O D1AltuSdkConfig implementa um builder para criar objetos de configuração.

        val config = D1AltuSdkConfig
            .setSlug("valor ha informar pela d1")
            .setSecret("valor ha informar pela d1")
            .setConversationHistoryType(ConversationHistoryType.ALWAYS)
            .setWebSocketEnvironment(D1AltuSdkWebSocketEnvironment.DEV)
            .setEnvironment(D1AltuSdkEnvironment.DEV)

# Métodos disponiveis do object D1AltuSdkConfig
    
Identificação do Cliente:

    setSlug("valor ha informar pela d1")

Chave de acesso fornecida pelo canal App:

    setSecret("valor ha informar pela d1")

Define o comportamento para historico de conversas:

    setConversationHistoryType(ConversationHistoryType.ALWAYS)

Define o ambiente que sera usado do web socket:

    setWebSocketEnvironment(D1AltuSdkWebSocketEnvironment.DEV)

Define o ambiente que sera usado:

    setEnvironment(D1AltuSdkEnvironment.DEV)
    
    
# Métodos disponiveis do object D1AltuSdk


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
    
# Problemas comuns
    TBD
