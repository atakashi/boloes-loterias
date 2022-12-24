# Bolões : Loterias

## Descrição
Projeto para gerar jogos de loteria.


## _Roadmap_
Neste momento, o projeto esta sendo construido para obter os jogos da mega-sena para a geração de jogos que ainda não foram sorteados.

## Configuração

### Instalação do certificado público SSL da CEF
Em **Dez/2022**, o serviço que fornece os resultados dos jogos exige o uso de um certicado público SSL especifico.

Este serviço esta na URL <https://servicebus2.caixa.gov.br/portaldeloterias/api/>.

#### Obter certificado público SSL
O atual certificado válido (`servicebus2.caixa.gov.br.cer`) esta gravado no diretório `resources/ssl/` do projeto.

**Observação**: Este certificado expira em **4º-feira, 14 de junho de 2023, as 20:59:59 horário de Brasília**.

Para baixar um novo certificado devemos seguir os seguintes passos:

- Abra a URL <https://servicebus2.caixa.gov.br/portaldeloterias/api/> em um navegador Chrome;

- Clique no cadeado ao lado da URL;

![Site Information pop-up](docs/images/ssl/01-site-information-pop-up.png)

- Selecione o item 'Connection is secure';

![Certificate is Valid pop-up](docs/images/ssl/02-certificate-is-valid-pop-up.png)

- Selecione o item 'Certificate is valid';

- Seleciona a aba 'Details';

- Selecione o certificado de nome `servicebus2.caixa.gov.br.cer`;

- Clique no botão 'Export';

![Details pop-up](docs/images/ssl/03-details-pop-up.png)


#### Instalar no servidor
Para instalar o certificado SSL devemos executar o seguinte comando:

```console
$ ./keytool -importcert -alias "servicebus2.caixa.gov.br.cer" -keystore {JAVA_HOME}/lib/security/cacerts -file {PathToDownloads}/servicebus2.caixa.gov.br.cer
```

#### _Endpoints_ da CEF
Existem duas formas de se obter os resultados dos jogos da loteria[^1].

Neste momento, optamos pela forma mais simples, de obter apenas um único resultado.
Esta forma é mais simples, pois os dados já vem em forma de JSON.

[^1]: _Link_ com mais detalhes: <https://pt.stackoverflow.com/questions/47597/como-posso-pegar-os-resultados-das-loterias>

## Detalhes Técnicos

### Técnicas Heurísicas para acelerar a pesquisa de jogos já sorteados
Para esta primeira versão, foi-se implementado o uso do algoritmo de _hash_ [MD5](https://pt.wikipedia.org/wiki/MD5).

Mas para uma segunda versão será implementando o algoritmo de [Bloom Filter](https://en.wikipedia.org/wiki/Bloom_filter).
Este tem um melhor tratamento para colisões e grande quantidade de elementos.

