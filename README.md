# ROBOCOP
ROBOCOP es una práctica de un sistema de robots creado a partir del proyecto ROSACE, para la asignatura de Desarrollo De Aplicaciones y Servicios Inteligentes (DASI) del Máster de Ingeniería Enformática de la Universidad Complutense de Madrid

###EJECUCION
Para arrancarlo una vez descargado e instalado el proyecto en un IDE ( Net beans o Eclipse ) es necesario:
- Localizar la clase ArranqueSistemaConCrtlGestorO que se encuentra en la carpeta:  
  - <b>`src/infraestructura/ClasesGeneradorasOrganizacion/`</b>
- Pasar como primer argumento uno de los ficheros que se encuentran en:
  - <b>`config/icaro/aplicaciones/descricionOrganizaciones/robocop/`</b> (hay que especificar que la carpeta donde se encuentra es robocop)
- Pasar como segundo argumento uno de los ficheros txt que se encuentra en:
  - <b>`config/icaro/aplicaciones/mapas/`</b> (solo hay que pasar en nombre del archivo, incluida la extensión, directamente se buscan los mapas a cargar en dicha carpeta)

Ejemplo de combinaciones de organizaciones y mapas que se pueden ejecutar actualmente son:
- <b>`robocop/descripcion_policias_comisaria mapa.txt`</b>
- <b>`robocop/descripcion_policias_comisaria mapaGrande.txt`</b>
- <b>`robocop/descripcion_policias_comisaria mapaGrandeConEquipos.txt`</b>
- <b>`robocop/descripcion_muchos_robos mapaMuchosAgentes.txt`</b>

###CREACION DE ORGANIZACIONES
Para Crear fácilmente nuevas organizaciones con más o menos agentes hay que:
  - En la parte del XML marcada con la etiqueta `<icaro:componentesGestionados>` añadir o quitar Agentes de Aplicación con sus ids, tantos como agentes totales se quiera tener
    - Ejemplo: 
    ```xml
    <icaro:componentesGestionados>
        <icaro:componenteGestionado refId="AgenteControladorSimuladorRosaceReactivo1" tipoComponente="AgenteAplicacion"/>
        <icaro:componenteGestionado refId="Policia1" tipoComponente="AgenteAplicacion"/>
        <icaro:componenteGestionado refId="Policia2" tipoComponente="AgenteAplicacion"/>
        <icaro:componenteGestionado refId="Policia3" tipoComponente="AgenteAplicacion"/>
        <icaro:componenteGestionado refId="Policia4" tipoComponente="AgenteAplicacion"/>
        <icaro:componenteGestionado refId="Ladron1" tipoComponente="AgenteAplicacion"/>
        <icaro:componenteGestionado refId="Ladron2" tipoComponente="AgenteAplicacion"/>
        <icaro:componenteGestionado refId="Ladron3" tipoComponente="AgenteAplicacion"/>
        <icaro:componenteGestionado refId="Comisaria" tipoComponente="AgenteAplicacion"/>
		</icaro:componentesGestionados>
      ```
  - En la parte etiquetada por `<icaro:AgentesAplicacion>` definir de que tipo son los agentes definidos antes, donde pueden ser de tipo `AgenteAplicacionPolicia`, `AgenteAplicacionLadron` y `AgenteAplicacionComisaria` (Es importante tener en cuenta que solo se debe definir un agente de tipo comisaria)
  - Ejemplo:
  
  ```xml
  <icaro:AgentesAplicacion>
      <icaro:Instancia 
      	id="AgenteControladorSimuladorRosaceReactivo1" 
    		refDescripcion="AgenteAplicacionAgteControladorSimuladorRosace">
      </icaro:Instancia>
      <icaro:Instancia 
      	id="Policia1" 
      	refDescripcion="AgenteAplicacionPolicia">
      </icaro:Instancia>
      <icaro:Instancia 
      	id="Policia2" 
      	refDescripcion="AgenteAplicacionPolicia">
      </icaro:Instancia>
      <icaro:Instancia 
      	id="Policia3" 
      	refDescripcion="AgenteAplicacionPolicia">
      </icaro:Instancia>
      <icaro:Instancia 
      	id="Policia4" 
      	refDescripcion="AgenteAplicacionPolicia">
      </icaro:Instancia>
      <icaro:Instancia 
      	id="Ladron1" 
      	refDescripcion="AgenteAplicacionLadron">
      </icaro:Instancia>
      <icaro:Instancia 
      	id="Ladron2" 
      	refDescripcion="AgenteAplicacionLadron">
      </icaro:Instancia>
      <icaro:Instancia 
      	id="Ladron3" 
      	refDescripcion="AgenteAplicacionLadron">
      </icaro:Instancia>
      <icaro:Instancia 
      	id="Comisaria" 
    		refDescripcion="AgenteAplicacionComisaria">
      </icaro:Instancia>
	</icaro:AgentesAplicacion>
	```
###CREACION DE MAPAS
Los mapas tienen la siguiente forma:
  - Primero el número de filas del mapa
  - Segundo el número de columnas del mapa
  - Luego la representación del mapa en caracteres
    - `C` para la comisaria
    - `S`para calles, los agentes tienen plena movilidad en ellas, en las demás casillas sólo es posible moverse de abajo-arriba para entrar a la casilla y arriba-abajo para salir, <b>los agentes son inteligentes y saben usar las puertas</b>
    - `H`para casas (en ellas se puede robar)
    - `A` para almacenes, son edificios en los que no se puede robar)
  - Por último y en una línea por agente el id del agente, su coordenada X inicial, su coordenada Y inicial y el nombre de su sequipo en caso de que sean ladrones, atributos separados por `;` (es importante que haya tantos agentes como se ha definido en la organización, así como que usen los mismo ids)
  ```
  Filas
  Columnas
  SS....SS
  .      .
  .      .
  .      .
  CH....HH
  IdAgente;X;Y;Equipo
  IdAgente2;X;Y;
  IdAgente3;X;Y;Equipo
  ```
  Ejemplo de mapa:
  ```
  8
  8
  CHHHHHHH
  SSSSSSSS
  HHSHSHHH
  SSSSSSSS
  SHHHHHHS
  SSSSSSSS
  HSHHHHSH
  SSSSSSSS
  Policia1;0;5
  Policia2;7;3
  Policia3;1;3
  Policia4;7;6
  Ladron1;5;3;EquipoA
  Ladron2;1;1;EquipoA
  Ladron3;7;6;ABC
  Comisaria;0;0;
  
  ```
