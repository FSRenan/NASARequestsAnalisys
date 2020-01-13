# NASA Requests Analisys

O projeto tem como objetivo analisar os seguintes pontos das requisições ao servidor da NASA Kennedy Space Center WWW na Flórida do período específico de julho e agosto de 1995:

• Número de hosts únicos

• Total de erros 404

• Os 5 URLs que mais causaram o erro 404

• Quantidade de erros 404 por dia

• O total de bytes retornados

[Fonte oficial dataset](​http://ita.ee.lbl.gov/html/contrib/NASA-HTTP.html)



## Execução

A aplicação foi desenvolvida em Spark utilizando a linguagem Scala. Os datasets são lidos da pasta NASAInputFiles, após sua leitura é efetuado o processamento para coleta dos dados acima, gerando o resultado da análise no console da aplicação.

### Resultado obtido:


**Arquivos processados:** access_log_Aug95, access_log_Jul95

**HOSTS únicos:** 9268

**Total de erros 404:** 20901

**5 URLs com mais erros 404:**

       Host: hoohoo.ncsa.uiuc.edu | Quantidade de erros 404: 251

       Host: piweba3y.prodigy.com | Quantidade de erros 404: 157

       Host: jbiagioni.npt.nuwc.navy.mil | Quantidade de erros 404: 132

       Host: piweba1y.prodigy.com | Quantidade de erros 404: 114

       Host: www-d4.proxy.aol.com | Quantidade de erros 404: 91


**Total de erros 404 por dia:**

       Dia: Sat Jul 01 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 316
       Dia: Sun Jul 02 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 291
       Dia: Mon Jul 03 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 474
       Dia: Tue Jul 04 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 359
       Dia: Wed Jul 05 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 497
       Dia: Thu Jul 06 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 640
       Dia: Fri Jul 07 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 570
       Dia: Sat Jul 08 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 302
       Dia: Sun Jul 09 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 348
       Dia: Mon Jul 10 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 398
       Dia: Tue Jul 11 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 471
       Dia: Wed Jul 12 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 471
       Dia: Thu Jul 13 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 532
       Dia: Fri Jul 14 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 413
       Dia: Sat Jul 15 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 254
       Dia: Sun Jul 16 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 257
       Dia: Mon Jul 17 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 406
       Dia: Tue Jul 18 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 465
       Dia: Wed Jul 19 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 639
       Dia: Thu Jul 20 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 428
       Dia: Fri Jul 21 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 334
       Dia: Sat Jul 22 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 192
       Dia: Sun Jul 23 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 233
       Dia: Mon Jul 24 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 328
       Dia: Tue Jul 25 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 461
       Dia: Wed Jul 26 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 336
       Dia: Thu Jul 27 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 336
       Dia: Fri Jul 28 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 94
       Dia: Tue Aug 01 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 243
       Dia: Thu Aug 03 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 304
       Dia: Fri Aug 04 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 346
       Dia: Sat Aug 05 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 236
       Dia: Sun Aug 06 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 373
       Dia: Mon Aug 07 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 537
       Dia: Tue Aug 08 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 391
       Dia: Wed Aug 09 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 279
       Dia: Thu Aug 10 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 315
       Dia: Fri Aug 11 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 263
       Dia: Sat Aug 12 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 196
       Dia: Sun Aug 13 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 216
       Dia: Mon Aug 14 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 287
       Dia: Tue Aug 15 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 327
       Dia: Wed Aug 16 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 259
       Dia: Thu Aug 17 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 271
       Dia: Fri Aug 18 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 256
       Dia: Sat Aug 19 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 209
       Dia: Sun Aug 20 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 312
       Dia: Mon Aug 21 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 305
       Dia: Tue Aug 22 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 288
       Dia: Wed Aug 23 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 345
       Dia: Thu Aug 24 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 420
       Dia: Fri Aug 25 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 415
       Dia: Sat Aug 26 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 366
       Dia: Sun Aug 27 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 370
       Dia: Mon Aug 28 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 410
       Dia: Tue Aug 29 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 420
       Dia: Wed Aug 30 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 571
       Dia: Thu Aug 31 00:00:00 GMT-03:00 1995 | Quantidade de erros 404: 526

**Total de bytes retornados:** 65524314915