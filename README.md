# Interfaz Gráfica en Java

Curso propuesto por el grupo de trabajo Semana de Ingenio y Diseño (**SID**) de la Universidad Distrital Francisco Jose de Caldas.

## Monitor

**Cristian Felipe Patiño Cáceres** - Estudiante de Ingeniería de Sistemas de la Universidad Distrital Francisco Jose de Caldas

# Clase 6

## Objetivos

* Identificar la creación de componentes gráficos dentro de la Single-page app y como incorporarles a  ventana principal.
* Comprender el concepto de enrutamiento y como se gestiona la visibilidad de los diferentes componentes dentro de la vista principal.
* Identificar la comunicación entre componentes para el paso de acciones e información.

# Antes de comenzar

Para continuar con el ejercicio deberá actualizar la carpeta resources ya que se han agregado nuevas imágenes. Estas las puede descargar en este mismo repositorio entrando a la carpeta **Clase6** seguido de **resources/img**. Puede notar que adentro existe una nueva carpeta llamada perfiles, esta también deberá ser agregada ya que tendrá utilidad en futuras clases.  


Recordando nuestro recorrido, el componente gráfico **login** esta listo y funcional, tiene una vista agradable para los usuarios, un código modularizado y optimizado, y realiza eventos por acción permitiendo entre otras cosas cerrar la aplicación, mostrar la información recibida del usuario o abrir la ventana principal.

<div align="center">
  <img  src="./resources/interfaz1.png">
  <p>Login de usuario en funcionamiento</p>
</div>

Nuestra ventana principal ya quedo lista para empezar a construir la Single-Page App a traves de componentes gráficos.

<div align="center">
  <img  src="./resources/interfaz2.png">
  <p>Vista Principal lista para construirse</p>
</div>

# Componentes Gráficos dentro de un Single-Page App y enrutamiento.

En esta clase veremos tres items importantes relacionados con la creación gestión y enrutamiento de componentes gráficos:
* Construcción e incorporación de componentes dentro de Single-Page app.
* Enrutamiento y gestión de visibilidad de componentes.


# Construcción e incorporación de componentes dentro de Single-Page app.

## Antecedentes
 
Ya verificamos que nuestra ventana principal cuenta con sus respectivos paneles por medio de sus colores, sin embargo ahora que serán reemplazados podemos quitarle el color ya que no serán necesarios. 

<div align="center">
  <img  src="./resources/codigo1.png">
  <p>Paneles de VistaPrincipalTemplate sin color</p>
</div>

Como realmente en la ventana principal no haremos uso de colores ni fuentes ni bordes también se removerá el uso del servicio RecursosService y la importación de la librería Color.

<div align="center">
  <img  src="./resources/codigo2.png">
  <p>A la izquierda se ve el código removido y a la izquierda el resultado</p>
</div>

## Creación e incorporación Componente Barra Titulo

Vamos a crear nuestro componente encargado de mostrar la barra de titulo, primero creamos el paquete **barraTitulo** dentro del paquete **Components**, dentro del paquete **barraTitulo** se crearán las clases **barraTituloTemplate** y **BarraTituloComponent**:

<div align="center">
  <img  src="./resources/paquetes1.png">
  <p>Creación componente barraTitulo con su respectivo paquete y clases</p>
</div>

Como recordaremos en nuestra clase la clase **Component** generalmente implementa de alguna interfaz que gestiona eventos, no es obligatorio pero en este caso el componente contendrá el botón que cierra la aplicación por lo que sera necesaria la implementación:
**implementación de interfaz**
```javascript
public class BarraTituloComponent implements ActionListener{
}
```
**implementación de métodos de la interfaz**
```javascript
@Override
public void actionPerformed(ActionEvent e) {
}
```

Creamos ahora un atributo de tipo de la clase **Template** y lo ejemplificamos enviándole por parámetro a la misma clase para realizar la inyección:

**Declaración**
```javascript
private BarraTituloTemplate barraTituloTemplate;
```

**Ejemplificación**
```javascript
this.barraTituloTemplate=  new BarraTituloTemplate(this); //dentro del constructor
```

A continuación mostraremos otra característica de las clases **Component**, tampoco es obligatorio realizarse pero habrán casos en los que si lo será, sobretodo con componentes que serán llamados desde otros como es el caso del componente actual. Se debe añadir un método **get** de su único atributo, en este caso de **barraTituloTemplate**.

```javascript
public BarraTituloTemplate getBarraTituloTemplate() {
    return this.barraTituloTemplate;
}
```

Ahora vamos con la clase **BarraTituloTemplate** esta al tener propiedades gráficas va heredar de un **JPanel**:
```javascript
public class BarraTituloTemplate extends JPanel{
}
```
Como recordamos esta recibe por parámetro dentro del constructor a la clase **Component** para igualarla con un atributo global de la clase, ademas obtendrá los servicios de **ObjGraficosService** y **RecursosService**:

**Declaración**
```javascript
private BarraTituloComponent barraTituloComponent;
private ObjGraficosService sObjGraficos;
private RecursosService sRecursos;
```

**recibimiento de servicios y clase Component**
```javascript
public BarraTituloTemplate(BarraTituloComponent barraTituloComponent){

        this.barraTituloComponent = barraTituloComponent;
        this.sObjGraficos= ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();
}
```

Como esta clase hereda de un **JPanel** es necesario darle propiedades gráficas asi que se realizan varias configuraciones:
```javascript
// Dentro del constructor al final
this.setSize(850, 50);
this.setBackground(Color.WHITE);
this.setLayout(null);
this.setVisible(true);
```

Note que el tamaño del panel de esta clase **Template** debe ser exactamente igual que el panel en la **VistaPrincipal** que reemplazara:

<div align='center'>
    <img  src='./resources/codigo3.png'>
    <p>Mismo tamaño de componente con Panel que reemplazara</p>
</div>

Vamos a agregar 3 elementos principales, un Logo, un titulo y un botón de cerrar, por lo que realizamos esto como lo veníamos haciendo en nuestras clases anteriores

**Declaración**
```javascript
//Declaración objetos gráficos
private JLabel lLogoApp, lTituloApp;
private JButton bCerrar;

//Declaración Objetos Decoradores
private ImageIcon iLogoApp, iDimAux;
private Font fontTituloBarra;
```
Se puede observar que vamos a utilizar una fuente que solo se utilizara para el titulo de la interfaz asi que se crea dentro de esta clase y no en **RecursosService**

**Método crearObjetosDecoradores:**

```javascript
public void crearObjetosDecoradores(){
    iLogoApp = new ImageIcon("Clase6/resources/img/logo_app.png");
    fontTituloBarra= new Font("Britannic Bold", Font.PLAIN, 24);
}
```

**método crearJButtons:**

```javascript
public void crearJButtons(){

    iDimAux = new ImageIcon(
        sRecursos.getICerrar().getImage().getScaledInstance(23, 23, Image.SCALE_AREA_AVERAGING)
    );
    bCerrar = sObjGraficos.construirJButton(
        null, 800, 10, 45, 30, sRecursos.getCMano(), iDimAux, null, 
        null, null, null, "c", false
    );
    bCerrar.addActionListener(barraTituloComponent);
    this.add(bCerrar);
}
```
Como el botón cerrar va a utilizar la misma imagen usada en el login se llama al servicio **RecursosService** para obtener dicha imagen compartida. También se observa que se agrego de una vez la propiedad **addActionListener**.

**Método crearJLabels:**
```javascript
public void crearJLabels(){
    iDimAux = new ImageIcon(
        iLogoApp.getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)
    );
    lLogoApp = sObjGraficos.construirJLabel(
        null, 20, 0, 50, 50, iDimAux, null, null, null
    );
    this.add(lLogoApp);

    lTituloApp = sObjGraficos.construirJLabel(
        "ProductList", 40, 5, 200, 40, null, sRecursos.getColorAzul(), null, fontTituloBarra
    );
    this.add(lTituloApp);
}
```
Note que en el label **lTituloApp** se esta usando la fuente exclusiva de esta clase.

**llamada de métodos de creación desde el constructor:**

```javascript
//Dentro del constructor después de obtener servicios 
this.crearObjetosDecoradores();
this.crearJLabels();
this.crearJButtons();
```

Nuestra clase **Template** del componente **barraTitulo** esta lista, para nuestra clase **Component** solo falta añadir un detalle y es la configuración del evento. En este caso sera el de salir de la aplicación que ya lo vimos en la clase anterior:
```javascript
System.exit(0); //dentro del método implementado actionPerformed
```
Note que como en este componente solo existe un botón no es necesario realizar discriminación de acción de ningún tipo.

Nuestro Componente esta totalmente listo, pero falta incorporarlo a nuestra ventana principal:

### **Incorporación de componente en la Single-Page app**

Nos vamos a ubicar en nuestra clase **VistaPrincipalComponent**, allí vamos a declarar un objeto de nuestro componente **barraTitulo** y como se explico en la clase anterior debe hacerse el llamando exclusivamente a la clase **Component**:

**Declaración:**
```javascript
//Declaración componentes
private BarraTituloComponent barraTituloComponent;
```
**Ejemplificación:**
```javascript
//Dentro del constructor
this.barraTituloComponent = new BarraTituloComponent();
```

Ahora viene la parte **más importante** que es la incorporación del componente a la ventana, como queremos que al abrir la ventana este componente ya este incorporado realizaremos el siguiente proceso dentro del constructor:
* Primero debemos obtener el panel que sera reemplazado en este caso **pBarra** y llamaremos entonces al método **get** correspondiente de la clase **VistaPrincipalTemplate**:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPBarra();
```

* Le debemos indicar al panel que vamos a agregar un componente gráfico, para eso llamamos a su método de configuración **add**:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPBarra().add();
```

* Debemos especificar que vamos a incorporar dentro entonces dentro de los paréntesis llamamos a la clase **component**:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPBarra().add(barraTituloComponent);
```
* Sin embargo la clase **BarraTituloComponent** no cuenta con propiedades gráficas, es la case **BarraTituloTemplate** la que si las tiene, es por eso que el editor muestre un error con el código anterior, sin embargo si recordamos nuestra clase **BarraTituloComponent** tiene un método **get** que nos devuelve esta clase **Template** asi que la invocaremos:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPBarra().add(
    barraTituloComponent.getBarraTituloTemplate()
);
```
Por cuestiones de espacio horizontal se acomoda la linea de código.

Si corremos nuestra aplicación y abrimos nuestra ventana principal notamos que se ha incorporado exitosamente su primer componente gráfico:
<div align='center'>
    <img  src='./resources/interfaz3.png'>
    <p>Vista principal con su primer componente agregado</p>
</div>

Incluso al darle click al botón de cerrar este funcionara de forma adecuada.

## Creación e incorporación Componente Navegación Usuario

Vamos a repetir el mismo proceso para nuestro componente gráfico **navegacionUsuario**  este componente es el encargado de mostrar en pantalla los botones con los que el usuario podrá navegar dentro de la aplicación. Creamos a continuación su paquete dentro del paquete **components** con sus respectivas clases:

<div align='center'>
    <img  src='./resources/paquetes2.png'>
    <p>Creación de componente navegacionUsuario</p>
</div>

Empezamos con la clase **Component** como el componente tendrá botones vamos a necesitar la implementación de la interfaz **ActionListener** y su método:
```javascript
public class NavegacionUsuarioComponent implements ActionListener {

    @Override
	public void actionPerformed(ActionEvent e) {
    }
}
```

Creamos ahora un atributo de tipo de la clase **Template** y lo ejemplificamos enviándole por parámetro a la misma clase para realizar la inyección:

**Declaración**
```javascript
private NavegacionUsuarioTemplate navegacionUsuarioTemplate;
```

**Ejemplificación**
```javascript
this.navegacionUsuarioTemplate =  new NavegacionUsuarioTemplate(this); //dentro del constructor
```

Al ser este un componente que también sera incorporado, este también contara con su método **get** de su clase **Template** correspondiente:

```javascript
public NavegacionUsuarioTemplate getNavegacionUsuarioTemplate() {
    return this.navegacionUsuarioTemplate;
}
```

Ahora en nuestra clase **NavegacionUsuarioTemplate** esta debe heredar igualmente de un **JPanel**:

```javascript
public class NavegacionUsuarioTemplate extends JPanel{
}
```

Se obtienen los servicios y la clase **Component** desde el constructor:

**Declaración de servicios y clase Component**
```javascript
private NavegacionUsuarioComponent navegacionUsuarioComponent;
private ObjGraficosService sObjGraficos;
private RecursosService sRecursos;
```

**Obtención de servicios y objeto de clase Component**
```javascript
public NavegacionUsuarioTemplate(NavegacionUsuarioComponent navegacionUsuarioComponent){
    this.navegacionUsuarioComponent = navegacionUsuarioComponent;
    this.sObjGraficos= ObjGraficosService.getService();
    this.sRecursos = RecursosService.getService();
}
```

Se dan las propiedades gráficas al componente gráfico y se debe prestar atención al tamaño ya que debe ser el mismo al panel en la ventana principal que va a reemplazar:
```javascript
// Dentro del constructor
this.setSize(250, 700);
this.setLayout(null);
this.setVisible(true);
```

Este componente va a contener El nombre del usuario con una foto de el, un eslogan y una serie de botones para que el usuario pueda navegar dentro de la aplicación, para tener una separación dentro se tendrán 2 paneles, uno que muestra la información del usuario (nombre, foto, eslogan) y el otro que muestra los botones de navegación.

**Declaraciones:**
```javascript
//Declaración objetos gráficos
private JPanel pSuperior, pInferior;
private JLabel lNombreUsuario, lEslogan, lImagenUsuario, lIconoUsuario;
private JButton bInicio, bPerfil, bAmigos, bProductos, bConfiguracion, bCerrarSesion;

//Declaración Objetos Decoradores
private ImageIcon iIconoUsuario, iInicio, iPerfil, iAmigos, iProductos, 
iConfiguracion, iCerrarSesion, iImagenUsuario, iDimAux;
```

**Método construirJPanels:**

```javascript
public void crearJPanels(){

    this.pSuperior = sObjGraficos.construirJPanel(
        0, 0, 250, 300, sRecursos.getColorAzul(), null
    );
    this.add(pSuperior);

    this.pInferior = sObjGraficos.construirJPanel(
        0, 300, 250, 400, sRecursos.getColorAzul(), null
    );
    this.add(pInferior);
}
```

**Método crearObjetosDecoradores:**

```javascript
public void crearObjetosDecoradores(){

    this.iIconoUsuario = new ImageIcon("Clase6/resources/img/usuario_navegacion.png");
    this.iInicio = new ImageIcon("Clase6/resources/img/inicio.png");
    this.iPerfil = new ImageIcon("Clase6/resources/img/perfil.png");
    this.iAmigos = new ImageIcon("Clase6/resources/img/amigos.png");
    this.iProductos = new ImageIcon("Clase6/resources/img/productos.png");
    this.iConfiguracion = new ImageIcon("Clase6/resources/img/configuracion.png");
    this.iCerrarSesion = new ImageIcon("Clase6/resources/img/salir.png");
    this.iImagenUsuario = new ImageIcon("Clase6/resources/img/perfiles/perfil1.png");
}
```

**Método crearJLabels:**
```javascript
public void crearJLabels(){

    iDimAux = new ImageIcon(
        iIconoUsuario.getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING)
    );
    this.lIconoUsuario = sObjGraficos.construirJLabel(
        null, 10, 20, 40, 40, iDimAux, null, null, null
    );
    this.pSuperior.add(lIconoUsuario);

    this.lNombreUsuario = sObjGraficos.construirJLabel(
        "Nombre de Usuario", 40, 20, 200, 40, null, Color.WHITE, null, sRecursos.getFontTitulo()
    );
    this.pSuperior.add(lNombreUsuario);

    iDimAux = new ImageIcon(
        iImagenUsuario.getImage().getScaledInstance(180, 180, Image.SCALE_AREA_AVERAGING)
    );
    this.lImagenUsuario = sObjGraficos.construirJLabel(
        null, (this.pSuperior.getWidth()-180)/2, 75, 180, 180, iDimAux, null, null, null
    );
    this.pSuperior.add(lImagenUsuario);

    this.lEslogan = sObjGraficos.construirJLabel(
        "<html><div align='center'> Nuestros clientes son <br/>lo mas importante</div></html>",  (this.pSuperior.getWidth()-180)/2, 265, 180, 40, null, Color.WHITE, null, sRecursos.getFontPequeña()
    );
    this.pSuperior.add(lEslogan);
}
```

Noten que en el label **lEslogan** hay algo diferente, y es que cuando enviamos un texto estamos enviando unas etiquetas **HTML** esto es debido a que no podemos dar saltos de linea dentro de un label de forma normal es decir si escribimos **\n** no va a funcionar, es por eso que hacemos uso de etiquetas html para poder dar saltos de linea a nuestro texto y ademas brindar de varias otras características como: 
* **`<html>`** indica que se va a iniciar un formato html y se debe cerrar al final con **`</html>`**.
* **`<div align='center'>`** Le da a nuestro texto propiedad de centrado, la etiqueta **div** debe tener una etiqueta de cerrado **`</div>`**.
* **`<div align='justify'>`** Le da a nuestro texto propiedad de texto justificado, la etiqueta **div** debe tener una etiqueta de cerrado **`</div>`**.
* **`<br/>`** indica un salto de linea, esto es solo cuando queremos dar un salto de linea en una parte en especifico, si no ponemos esta etiqueta de todos modos el html realiza el salto de linea automático una vez ocupa todo el espacio de ancho.

**Método crear JButtons**

```javascript
public void crearJButtons(){
        iDimAux = new ImageIcon(
            iInicio.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        this.bInicio = sObjGraficos.construirJButton(
            "      Inicio", 30, 30, 200, 40, sRecursos.getCMano(), 
            iDimAux, sRecursos.getFontBotones(), null, Color.WHITE, null, "l", false
        );
        this.bInicio.addActionListener(navegacionUsuarioComponent);
        this.pInferior.add(bInicio);

        iDimAux = new ImageIcon(
            iPerfil.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        this.bPerfil = sObjGraficos.construirJButton(
            "      Perfil", 30, 80, 200, 40, sRecursos.getCMano(), 
            iDimAux, sRecursos.getFontBotones(), null, Color.WHITE, null, "l", false
        );
        this.bPerfil.addActionListener(navegacionUsuarioComponent);
        this.pInferior.add(bPerfil);

        iDimAux = new ImageIcon(
            iAmigos.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        this.bAmigos = sObjGraficos.construirJButton(
            "      Amigos", 30, 130, 200, 40, sRecursos.getCMano(), 
            iDimAux, sRecursos.getFontBotones(), null, Color.WHITE, null, "l", false
        );
        this.bAmigos.addActionListener(navegacionUsuarioComponent);
        this.pInferior.add(bAmigos);

        iDimAux = new ImageIcon(
            iProductos.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        this.bProductos = sObjGraficos.construirJButton(
            "      Productos", 30, 180, 200, 40, sRecursos.getCMano(), 
            iDimAux, sRecursos.getFontBotones(), null, Color.WHITE, null, "l", false
        );
        this.bProductos.addActionListener(navegacionUsuarioComponent);
        this.pInferior.add(bProductos);

        iDimAux = new ImageIcon(
            iConfiguracion.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        this.bConfiguracion = sObjGraficos.construirJButton(
            "      Configuraciones", 30, 230, 200, 40, sRecursos.getCMano(), 
            iDimAux, sRecursos.getFontBotones(), null, Color.WHITE, null, "l", false
        );
        this.bConfiguracion.addActionListener(navegacionUsuarioComponent);
        this.pInferior.add(bConfiguracion);

        iDimAux = new ImageIcon(
            iCerrarSesion.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
        );
        this.bCerrarSesion = sObjGraficos.construirJButton(
            "      Cerrar Sesión", 30, 280, 200, 40, sRecursos.getCMano(), 
            iDimAux, sRecursos.getFontBotones(), null, Color.WHITE, null, "l", false
        );
        this.bCerrarSesion.addActionListener(navegacionUsuarioComponent);
        this.pInferior.add(bCerrarSesion);
    }
```

Estos botones tienen unas características peculiares:
* Todos cuentan con una imagen y ademas un texto, esto es posible y nuestro servicio **ObjGraficosService** esta configurado para estos casos.
* El botón esta vez cuenta con una dirección hacia la izquierda por lo que se envía como argumento **"l"** para el parámetro **dirección**.
* No tienen fondo por lo que se envía como argumento un **false** para el parámetro **esSolido**.

**llamada de métodos de creación desde el constructor:**
```javascript
//Dentro del constructor después de obtener servicios 
this.crearObjetosDecoradores();
this.crearJPanels();
this.crearJLabels();
this.crearJButtons();
```

Nuestro componente gráfico esta casi listo solo falta realizar la configuración de los eventos de acción pero esto se discutirá en la siguiente sección **Enrutamiento y gestión de visibilidad de componentes**. por el momento vamos a incorporarlo en la vista principal

### **Incorporación de componente en la Single-Page app**

Nos vamos a ubicar nuevamente en nuestra clase **VistaPrincipalComponent**, allí vamos a declarar un objeto de nuestro componente **navegacionUsuario** y como se explico en la clase anterior debe hacerse el llamando exclusivamente a la clase **Component**:

**Declaración:**
```javascript
//Declaración componentes
private NavegacionUsuarioComponent navegacionUsuarioComponent;
```
**Ejemplificación:**
```javascript
//Dentro del constructor
this.navegacionUsuarioComponent = new NavegacionUsuarioComponent();
```

La incorporación la vamos a realizar de la misma manera que explicamos con el anterior componente gráfico, esta vez vamos a reemplazar el panel **pNavegacion**:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPNavegacion().add(
    navegacionUsuarioComponent.getNavegacionUsuarioTemplate()
);
```

Si corremos nuestra aplicación y abrimos nuestra ventana principal notamos que se ha incorporado exitosamente el segundo componente:
<div align='center'>
    <img  src='./resources/interfaz4.png'>
    <p>Vista principal con el componente Navegación usuario agregado</p>
</div>

Sin embargo al dar click sobre cualquiera de los botónes, estos no hacen nada aun, cuando se oprima cualquiera de los botones queremos ver el componente dentro del panel **pPrincipal** y esto se discutirá en la siguiente sección.

# Enrutamiento y gestión de visibilidad de componentes