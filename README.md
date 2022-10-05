# microservices-sistem-ead
Desenvolvimento de microservices sistema ead, realizado durante o curso da Micheli Brito no projeto Decoder



# API
Uma API e nada mais é do que uma aplicação cliente servidor que é utilizada então pra comunicar serviços, pra garantir a interoperabilidade em esses serviços.

Vamos utilizar isso muito aqui nessa plataforma EAD do projeto decoder porque vamos precisar fazer a comunicação síncrona entre determinados serviços vamos precisar criar essas apps 

# REST
REST é o modelo arquitetural, é um conjunto de padrões que trazem então boas práticas para o desenvolvimento dessas desenvolvimento dessas API's, então é muito importante utilizar esses padrões de boas práticas tanto no desenvolvimento e também para facilitar os clientes ou os serviços que vão utilizar e vão consumir essas API's

# RESTFULL
RESTFULL na verdade a implementação deste modelo arquitetural REST na prática enquanto que REST é algo abstrato RESTFUL é um padrão que vem como algo concreto é a como se fosse a implementação do modelo arquitetural REST em alguma API então por isso que a gente diz que mapeie ela é considerada RESTful quando ela tem implementada né quando ela possui a implementação deste conjunto de boas práticas leste 

# COMO IDENTIFICAR SE UMA API É RESTFULL OU NÃO?
Richardson Maturity Model

	 • Level Zero Services
	O nível zero de maturidade não utiliza recursos de URI, HTTP Methods e HATEOAS.
	
	 • Level One Services
	Richardson definiu que para uma API ser considerada que está com nível um ela deve utilizar de recursos bem definidos então aqui vocês podem ver que esses recursos eles têm que representar bem o que aquela API expõe. Por exemplo uma API de usuários, expõe usuários, uma API de cursos, expõe cursos, uma API de notificações expõe notificações.

	 • Level Two Services
	Uma API além de utilizar o protocolo HTTP além de possuir recursos bem definidos ela precisa utilizar em forma semântica os métodos HTTP utilizar os métodos get para buscar recursos, utilizar o método post para salvar o novo recurso, do método delete para deletar um recurso, utilizar PUT pra atualizar o recurso por diante não apenas um método para realizar todas essas ações, mas utilizar os diferentes métodos para essas diferentes ações e além disso, além de utilizar de forma definida cada um desses métodos também nos retornos é de acordo com cada processamento de acordo com cada resposta por exemplo utilizar dos retornos do grupo 100 para informações, do grupo 200 quando a gente tiver sucesso, do grupo 300 para redirecionamentos, do grupo 400 pra quando apresentar algum erro no cliente ou do grupo 500 quanto tiver um erro no servidor. E também temos a utilização do STATUS de acordo com cada resposta.
	
	## • Level Three Services
	Uma API para então atingir esse nível e realmente ser considerada RESTful ela precisa possuir e implementar de hipermídias que são os RATEOS que são essas hipermídias né são links é de navegação onde a gente mostra a possibilidade de caminhos para demais recursos dentro da apei então se vocês olharem aqui neste get users na hora que eu solicitei essa listagem né de usuários para o servidor ele me retornou tanto os Campos deste recurso e ele também me retornou esses Campos aqui Lins e vocês podem ver que por exemplo para cada usuário ele me traz um link especifico então os detalhes de cada um desses recursos então dessa forma essa p ela pode sim ser considerada RESTful porque ela tem a atingiu todos esses pilares.
	
Então é muito importante a gente construir a API dentro desses padrões para que seja fácil tanto na hora de desenvolver manter ali um padrão de desenvolvimento e principalmente na hora que a gente for consumir essa API seja o microservice ou seja um cliente terceiro.
