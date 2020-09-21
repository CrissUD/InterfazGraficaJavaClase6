# Interfaz Gráfica en Java

Curso propuesto por el grupo de trabajo Semana de Ingenio y Diseño (**SID**) de la Universidad Distrital Francisco Jose de Caldas.

## Monitor

**Cristian Felipe Patiño Cáceres** - Estudiante de Ingeniería de Sistemas de la Universidad Distrital Francisco Jose de Caldas

# Clase 6

## Objetivos

* Identificar la forma de incorporar componentes gráficos dentro de una **Single-page app** para que puedan ser visualizados desde la ventana principal.
* Comprender el concepto de navegación y como se gestiona la visibilidad de los diferentes componentes dentro de la vista principal.
* Reconocer la comunicación entre componentes para el paso de acciones e información.
* Considerar la importancia del control en la creación de objetos en memoria y como gestionar la producción desmesurada de estos.

# Antes de comenzar

### **Actualización de Imágenes en recursos**

Para continuar con la lección deberá actualizar la carpeta **resources/images** ya que se han agregado nuevas imágenes. Estas las puede descargar en este mismo repositorio entrando a la carpeta **Clase6** seguido de **resources/images**. Puede notar que adentro existe una nueva carpeta llamada perfiles, esta también deberá ser agregada ya que tendrá utilidad.  
<div align='center'>
    <img  src='https://i.imgur.com/ddUtsZN.png'>
    <p>Carpeta perfiles dentro de resources/images en el repositorio</p>
</div>

### **Ajustes en el servicio Recursos Service**

En el servicio **RecursosService** se crea un nuevo objeto decorador **Font**:

**Declaración:**
```javascript
// Al inicio del servicio
private Font fontLigera;
```

**Ejemplificación:**
```javascript
// Dentro del método crearFuentes
fontLigera = new Font("LuzSans-Book", Font.PLAIN, 12);
```

**Método get:**
```javascript
public Font getFontLigera() { return fontLigera; }
```

También se crea un objeto tipo **ImageIcon**:

**Declaración:**
```javascript
// Al inicio del servicio
private ImageIcon iMinimizar;
```

**Ejemplificación:**
```javascript
// Dentro del método crearImagenes
iMinimizar = new ImageIcon("Clase6/resources/images/minimizar.png");
```

**Método get:**
```javascript
public ImageIcon getIMinimizar() { return iMinimizar; }
```

### **Recordatorio**

Recordando el recorrido, el componente gráfico **login** esta listo y funcional, tiene una vista agradable para los usuarios, un código modularizado y optimizado, además realiza eventos por acción permitiendo cerrar la aplicación, mostrar la información recibida del usuario o abrir la ventana principal.

<div align="center">
  <img  src="https://i.imgur.com/jBS89yY.png">
  <p>Login de usuario en funcionamiento</p>
</div>

La ventana principal ya quedo lista para empezar a construir la Single-Page App a traves de componentes gráficos.

<div align="center">
  <img  src="https://i.imgur.com/bJeQrCS.png">
  <p>Vista Principal lista para construirse</p>
</div>

# Componentes Gráficos dentro de un Single-Page App y navegación.

En esta sesión se verán tres items importantes relacionados con la creación, gestión y navegación de componentes gráficos:
* **Construcción e incorporación de componentes gráficos dentro de Single-Page app**.
* **Navegación y gestión de visibilidad de componentes gráficos**.
* **Control en la creación de componentes gráficos en memoria**.


# Construcción e incorporación de componentes gráficos dentro de Single-Page app.

## Ajustes
 
Ya se realizo una maquetización en la ventana principal y esta cuenta con sus respectivos paneles, los cuales se evidencian por medio de sus colores, sin embargo, su contenido será reemplazado, asi que se procede a retirar el color a los paneles. 

<div align="center">
  <img  src="https://i.imgur.com/k9HNMxp.png.png">
  <p>Paneles de VistaPrincipalTemplate sin color</p>
</div>

Realmente en la ventana principal no se usaran objetos decoradores asi que también se removerá el uso del servicio RecursosService y la importación de la librería Color.

<div align="center">
  <img  src="https://i.imgur.com/AYE7Vr1.png">
  <p>A la izquierda se ve el código removido y a la derecha el resultado</p>
</div>

## Creación e incorporación del Componente Barra Titulo

Se va a incorporar el componente encargado de mostrar la barra de titulo, primero se crea un nuevo paquete **barraTitulo** dentro del paquete **Components**, en el paquete **barraTitulo** se crearán las clases **BarraTituloTemplate** y **BarraTituloComponent**:

<div align="center">
  <img  src="https://i.imgur.com/o6b7tKc.png">
  <p>Creación componente barraTitulo con su respectivo paquete y clases</p>
</div>

Como se menciono en la sesión anterior, la clase **Component** puede implementar de alguna interfaz que gestiona eventos de ser necesario, en este caso el componente **barraTitulo** contendrá varios botones para realizar acciones como minimizar o cerrar la aplicación por lo que será necesaria la implementación:

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

Se crea el atributo que hace referencia a la clase **Template** y se ejemplifica enviándose como argumento una referencia a si misma con la palabra clave **this** para realizar la inyección de dependencias:

**Declaración**
```javascript
private BarraTituloTemplate barraTituloTemplate;
```

**Ejemplificación**
```javascript
// Dentro del constructor
this.barraTituloTemplate = new BarraTituloTemplate(this); 
```
Se debe añadir un método **get** de su único atributo **barraTituloTemplate**, para que otros componentes puedan acceder a la clase gráfica del componente como se explico con anterioridad.

```javascript
public BarraTituloTemplate getBarraTituloTemplate() {
  return this.barraTituloTemplate;
}
```

Ahora se codifica a la clase **BarraTituloTemplate**, esta al tener propiedades gráficas va heredar de un **JPanel**:
```javascript
public class BarraTituloTemplate extends JPanel{
}
```

Recibe por parámetro dentro del constructor a la clase **Component** para igualarla con un atributo global de la misma referencia para cerrar la inyección, ademas obtendrá los servicios de **ObjGraficosService** y **RecursosService**:

**Declaración:**
```javascript
private BarraTituloComponent barraTituloComponent;
private ObjGraficosService sObjGraficos;
private RecursosService sRecursos;
```

**Obtención de servicios y recibimiento de la clase Component:**
```javascript
public BarraTituloTemplate(BarraTituloComponent barraTituloComponent){
  this.barraTituloComponent = barraTituloComponent;
  this.sObjGraficos= ObjGraficosService.getService();
  this.sRecursos = RecursosService.getService();
}
```

Como la clase hereda de un **JPanel** es necesario darle propiedades gráficas asi que se realizan varias configuraciones:
```javascript
// Dentro del constructor al final
this.setSize(850, 50);
this.setBackground(Color.WHITE);
this.setLayout(null);
this.setVisible(true);
```

Note que el tamaño del panel de esta clase **Template** debe ser exactamente igual que el panel en la **VistaPrincipal** al que se incorporará, en este caso a el panel **pBarra**:

<div align='center'>
    <img  src='https://i.imgur.com/MuDDTDA.png'>
    <p>Mismo tamaño de componente con Panel al que se incorporará</p>
</div>

Se agregarán en el componente 4 objetos gráficos principales, un **Logo**, un **Título**, un **Botón de minimizar** y un **Botón de cerrar**, también se agregarán algunos objetos decoradores, por lo que se realiza el proceso de creación reflejado en las sesiones anteriores:

**Declaración**
```javascript
//Declaración objetos gráficos
private JLabel lLogoApp, lTituloApp;
private JButton bCerrar, bMinimizar;

//Declaración Objetos Decoradores
private ImageIcon iLogoApp, iDimAux;
private Font fontTituloBarra;
```
Se puede observar que se creará una fuente que solo se utilizará para el titulo de la interfaz asi que se crea dentro del componente especifico y no en **RecursosService**

**Método crearObjetosDecoradores:**
```javascript
public void crearObjetosDecoradores(){
  iLogoApp = new ImageIcon("Clase6/resources/images/logo_app.png");
  fontTituloBarra= new Font("Britannic Bold", Font.PLAIN, 24);
}
```

**Método crearJButtons:**
```javascript
public void crearJButtons(){
  // BOTÓN CERRAR ----------------------------------------------------
  iDimAux = new ImageIcon(
    sRecursos.getICerrar().getImage()
      .getScaledInstance(23, 23, Image.SCALE_AREA_AVERAGING)
  );
  bCerrar = sObjGraficos.construirJButton(
    null, 
    800, 10, 45, 30,
    sRecursos.getCMano(), 
    iDimAux, 
    null, null, null, null, 
    "c", 
    false
  );
  bCerrar.addActionListener(barraTituloComponent);
  this.add(bCerrar);

  // BOTÓN MINIMIZAR ----------------------------------------------------
  iDimAux = new ImageIcon(
    sRecursos.getIMinimizar().getImage()
      .getScaledInstance(28, 28, Image.SCALE_AREA_AVERAGING)
  );
  bMinimizar = sObjGraficos.construirJButton(
    null,
    750, 10, 45, 30,
    sRecursos.getCMano(),
    iDimAux,
    null, null, null, null,
    "c",
    false
  );
  bMinimizar.addActionListener(barraTituloComponent);
  this.add(bMinimizar);
}
```
Como el botón cerrar va a utilizar la misma imagen usada en el login se llama al servicio **RecursosService** para obtener dicha imagen compartida. También se observa que se agrego de una vez la propiedad **addActionListener** en ambos botones.

**Método crearJLabels:**
```javascript
public void crearJLabels(){
  // LABEL LOGO APP--------------------------------------------------------------------
  iDimAux = new ImageIcon(
    iLogoApp.getImage()
      .getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)
  );
  lLogoApp = sObjGraficos.construirJLabel(
    null,
    20, 0, 50, 50,
    null,
    iDimAux,
    null, null, null, null,
    "c"
  );
  this.add(lLogoApp);

  // LABEL TITULO APP--------------------------------------------------------------------
  lTituloApp = sObjGraficos.construirJLabel(
    "ProductList",
    40, 5, 200, 40,
    null, null,
    fontTituloBarra,
    null,
    sRecursos.getColorAzul(),
    null,
    "c"
  );
  this.add(lTituloApp);
}
```
Note que en el label **lTituloApp** se esta usando la fuente exclusiva de este componente gráfico.

**Llamada de métodos de creación desde el constructor:**
```javascript
//Dentro del constructor después de obtener servicios 
this.crearObjetosDecoradores();
this.crearJLabels();
this.crearJButtons();
```

Por ultimo se crean métodos **get** para los botones para puedan ser manipulados desde la clase **Component**:
```javascript
public JButton getBCerrar() { return bCerrar; }

public JButton getBMinimizar() { return bMinimizar; }
```

La clase **Template** del componente **barraTitulo** esta lista. Por otro lado para la clase **Component** solo falta añadir la configuración de los eventos de acción. En este caso serán el de minimizar y salir de la aplicación, primero se procede a crear una discriminación por objetos:
```javascript
@Override
public void actionPerformed(ActionEvent e) {
  if (e.getSource() == barraTituloTemplate.getBMinimizar())
    // Acción para minimizar
  if (e.getSource() == barraTituloTemplate.getBCerrar())
    // Acción para Cerrar
}
```
### **Comunicación bidireccional entre componentes gráficos**
Note que aunque el componente **barraTitulo** contiene los botones que realizan estas acciones, el componente que se debe encargar realmente de cerrar o minimizar la ventana debe ser **vistaPrincipal**. Así que se crean los métodos encargados de realizar estas acciones dentro de la clase **VistaPrincipalComponent**:
```javascript
// Dentro de la clase VistaPrincipalComponent
public void cerrar() {
    System.exit(0);
  }

public void minimizar() {
  this.vistaPrincipalTemplate.setExtendedState(Frame.ICONIFIED);
}
```

El anterior código para minimizar la ventana se debe llamar a la clase de la ventana **VistaPrincipalTemplate** y se usa el método:

* **setExtendedState:** Que recibe como parámetro un estado de la clase Frame y que cambia la perspectiva de la ventana haciendo posible acciónes como minimizar, expandir etc. El Argumento **Frame.ICONIFIED** le indica a la ventana que se minimize.

Es necesario para llamar a este estado importar la librería del Frame dentro de la clase VistaPrincipalComponent:
```javascript
import java.awt.Frame;
```

Ahora se debe llamar a los métodos correspondientes de la vista principal dentro del componente **barraTitulo**, sin embargo es necesario obtener una referencia del componente **vistaPrincipal**, así que se recibirá por parámetro un objeto que haga referencia a la clase component y luego se utilizará:
* **Declaración:**
```javascript
private VistaPrincipalComponent vistaPrincipalComponent;
```

* **Obtención de referencia por inyección**
```javascript
public BarraTituloComponent(VistaPrincipalComponent vistaPrincipalComponent) {
  this.vistaPrincipalComponent = vistaPrincipalComponent;
  ...
  ...
}
```

* **Uso de objeto recibido para llamar a los métodos:**
```javascript
@Override
public void actionPerformed(ActionEvent e) {
  if (e.getSource() == barraTituloTemplate.getBMinimizar())
    vistaPrincipalComponent.minimizar();
  if (e.getSource() == barraTituloTemplate.getBCerrar())
    vistaPrincipalComponent.cerrar();
}
```

En este caso se esta recibiendo una referencia del componente padre **VistaPrincipal** por el constructor creando así una inyección de dependencia y esto crea a su vez una comunicación bidireccional entre ambos componentes, Ahora el componente padre **VistaPrincipal** puede enviar peticiones a **barraTitulo** y viceversa. (Este proceso de comunicación bidireccional entre componentes se explica con mas detalle en la sección de navegación.) 

Esta comunicación entre componentes solo se debe realizar en caso de ser necesario, por lo general solamente existe una comunicación unidireccional donde el componente padre realiza peticiones al hijo nada mas.

El Componente esta totalmente listo, sin embargo, falta incorporarlo a la ventana principal:

### **Incorporación de componente en la Single-Page app**

En la clase **VistaPrincipalComponent** se va a declarar un objeto del componente **barraTitulo** y como se explico en la sesión anterior, debe hacerse el llamando exclusivamente a la clase **Component**:

**Declaración:**
```javascript
//Declaración componentes
private BarraTituloComponent barraTituloComponent;
```
**Ejemplificación:**
```javascript
//Dentro del constructor
this.barraTituloComponent = new BarraTituloComponent(this);
```

Note que dentro de la ejemplificación se enviá una referencia asi misma con la palabra clave **this** para terminar la inyección de dependencias.

Ahora se realiza la parte **más importante** que es la incorporación del componente a la ventana, como se quiere que al abrir la ventana este componente ya este incorporado se realiza el siguiente proceso dentro del constructor:
* Primero se obtiene el panel que incorporará al componente, en este caso **pBarra** y para esto se llama al método **get** correspondiente de la clase **VistaPrincipalTemplate**:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPBarra();
```

* Una vez obtenido el panel, se le indica al panel que se agregará un objeto, para eso se llama a su método de configuración **add**:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPBarra().add();
```

* Se debe especificar que se va a incorporar, entonces dentro de los paréntesis se llama a la clase **Component** que antes se ha ejemplificado:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPBarra().add(barraTituloComponent);
```
* Sin embargo la clase **BarraTituloComponent** no cuenta con propiedades gráficas, es la clase **BarraTituloTemplate** la que si las tiene ya que hereda de un **JPanel**, es por eso que el editor muestra un error con el código anterior. Sin embargo, la clase **BarraTituloComponent** tiene un método **get** que devuelve la clase **Template**, asi que se invoca:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPBarra().add(
    barraTituloComponent.getBarraTituloTemplate()
);
```
Por cuestiones de espacio horizontal se acomoda la linea de código.

Si se ejecuta la aplicación y abrimos la ventana principal se observa que se ha incorporado exitosamente su primer componente gráfico:
<div align='center'>
    <img  src='https://i.imgur.com/61cHTHl.png'>
    <p>Vista principal con su primer componente agregado</p>
</div>

Incluso al darle click al botón de cerrar o al botón minimizar estos funcionarán de forma adecuada.

## Creación e incorporación del Componente Navegación Usuario

Se va a repetir el mismo proceso para el componente gráfico **navegacionUsuario**  este componente es el encargado de mostrar en pantalla los botones con los que el usuario podrá navegar dentro de la aplicación. Se crea a continuación su paquete con sus respectivas clases dentro del paquete **components**:

<div align='center'>
    <img  src='https://i.imgur.com/Afy9X3H.png'>
    <p>Creación del componente gráfico navegacionUsuario</p>
</div>

Se empieza con la clase **Component**, como el componente contendrá botones, es necesaria la implementación de la interfaz **ActionListener** y su método:
```javascript
public class NavegacionUsuarioComponent implements ActionListener {

    @Override
	public void actionPerformed(ActionEvent e) {
    }
}
```

Se crea el atributo que referencia a la clase **Template** y se ejemplifica enviándose como argumento a si mismo con la palabra clave **this** para realizar la inyección:

**Declaración**
```javascript
private NavegacionUsuarioTemplate navegacionUsuarioTemplate;
```

**Ejemplificación**
```javascript
//dentro del constructor
this.navegacionUsuarioTemplate =  new NavegacionUsuarioTemplate(this); 
```

Se debe generar también el método **get** de su clase **Template** correspondiente:
```javascript
public NavegacionUsuarioTemplate getNavegacionUsuarioTemplate() {
    return this.navegacionUsuarioTemplate;
}
```

Ahora en la clase **NavegacionUsuarioTemplate** esta debe heredar de un **JPanel**:

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

**Obtención de servicios y recibimiento del objeto de clase Component**
```javascript
public NavegacionUsuarioTemplate(NavegacionUsuarioComponent navegacionUsuarioComponent){
    this.navegacionUsuarioComponent = navegacionUsuarioComponent;
    this.sObjGraficos= ObjGraficosService.getService();
    this.sRecursos = RecursosService.getService();
}
```

Se dan las propiedades gráficas al componente gráfico y se debe prestar atención al tamaño ya que debe ser el mismo al panel en la ventana principal que lo va a incorporar, en este caso el panel **pNavegacion**:
```javascript
// Dentro del constructor
this.setSize(250, 700);
this.setLayout(null);
this.setVisible(true);
```

Este componente va a contener los siguientes objetos gráficos:

* Panel que muestra información del usuario:
    * Label con un icono de usuario.
    * Label con el nombre del usuario.
    * Label con fotografiá del usuario.
    * Label con un pequeño eslogan.
* Panel que contiene los botones de navegación:
    * Botones de navegación (serán 6 en total)

**Declaraciones:**
```javascript
//Declaración objetos gráficos
private JPanel pSuperior, pInferior;
private JLabel lNombreUsuario, lEslogan, lImagenUsuario, lIconoUsuario;
private JButton bInicio, bPerfil, bAmigos, bProductos, bConfiguracion, bCerrarSesion;

//Declaración Objetos Decoradores
private ImageIcon iIconoUsuario, iInicio, iPerfil, iAmigos, iProductos, 
iConfiguracion, iCerrarSesion, iImagenUsuario, iDimAux;
private Border bVacio;
```

**Método construirJPanels:**
```javascript
public void crearJPanels(){
  this.pSuperior = sObjGraficos.construirJPanel(
    0, 0, 250, 300, 
    sRecursos.getColorAzul(), 
    null
  );
  this.add(pSuperior);

  this.pInferior = sObjGraficos.construirJPanel(
    0, 300, 250, 400, 
    sRecursos.getColorAzul(), 
    null
  );
  this.add(pInferior);
}
```

**Método crearObjetosDecoradores:**
```javascript
public void crearObjetosDecoradores(){
    this.iIconoUsuario = new ImageIcon("Clase6/resources/images/usuario_navegacion.png");
    this.iInicio = new ImageIcon("Clase6/resources/images/inicio.png");
    this.iPerfil = new ImageIcon("Clase6/resources/images/perfil.png");
    this.iAmigos = new ImageIcon("Clase6/resources/images/amigos.png");
    this.iProductos = new ImageIcon("Clase6/resources/images/productos.png");
    this.iConfiguracion = new ImageIcon("Clase6/resources/images/configuracion.png");
    this.iCerrarSesion = new ImageIcon("Clase6/resources/images/salir.png");
    this.iImagenUsuario = new ImageIcon("Clase6/resources/images/perfiles/perfil1.png");
    this.bVacio = new EmptyBorder(2, 20, 2, 2);
}
```
Se puede observar que se crea un borde vació, este borde será utilizado para crear un espació interno o Padding entre el borde de los botones y el contenido de los mismos.

**Método crearJLabels:**
```javascript
public void crearJLabels(){
  // LABEL ICONO USUARIO--------------------------------------------------------------------
  iDimAux = new ImageIcon(
    iIconoUsuario.getImage()
      .getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING)
  );
  this.lIconoUsuario = sObjGraficos.construirJLabel(
    null,
    10, 20, 40, 40,
    null,
    iDimAux,
    null, null, null, null,
    "c"
  );
  this.pSuperior.add(lIconoUsuario);

  // LABEL NOMBRE USUARIO--------------------------------------------------------------------
  this.lNombreUsuario = sObjGraficos.construirJLabel(
    "Nombre de Usuario", 
    ((this.pSuperior.getWidth() - 200) / 2) + 10, 20, 200, 40,
    null, null, 
    sRecursos.getFontTitulo(), 
    null,
    Color.WHITE,
    null,
    "c"
  );
  this.pSuperior.add(lNombreUsuario);

  // LABEL IMAGEN USUARIO--------------------------------------------------------------------
  iDimAux = new ImageIcon(
    iImagenUsuario.getImage()
      .getScaledInstance(180, 180, Image.SCALE_AREA_AVERAGING)
  );
  this.lImagenUsuario = sObjGraficos.construirJLabel(
    null, 
    (this.pSuperior.getWidth() - 180) / 2, 75, 180, 180,
    null,
    iDimAux, 
    null, null, null, null,
    "c"
  );
  this.pSuperior.add(lImagenUsuario);

  // LABEL ESLOGAN--------------------------------------------------------------------
  this.lEslogan = sObjGraficos.construirJLabel(
    "<html><div align='center'> Nuestros clientes son <br/>lo mas importante</div></html>",
    (this.pSuperior.getWidth() - 180) / 2, 265, 180, 40, 
    null, null, 
    sRecursos.getFontLigera(), 
    null, 
    Color.WHITE, 
    null,
    "c");
  this.pSuperior.add(lEslogan);
}
```

Note que en el label **lEslogan** hay algo diferente, y es que el texto se ha escrito dentro de etiquetas **HTML**. Esto es debido a que java no permite dar saltos de linea dentro de un label, es decir que si se escribe **\n** que representa un salto de linea en consola no va a funcionar para los Labels, es por eso que se usan etiquetas HTML para poder dar saltos de linea al texto y ademas brindar de varias otras características como: 
* **`<html>`** indica que se va a iniciar un formato html y se debe cerrar al final con **`</html>`**.
* **`<div align='center'>`** Le da al texto propiedad de centrado, la etiqueta **div** debe tener una etiqueta de cerrado **`</div>`**.
* **`<div align='justify'>`** Le da al texto propiedad de texto justificado, la etiqueta **div** debe tener una etiqueta de cerrado **`</div>`**.
* **`<br/>`** indica un salto de linea, esto es solo cuando se quiere dar un salto de linea explicito, si no se coloca esta etiqueta de todos modos el html realiza el salto de linea automático una vez ocupa todo el espacio de ancho.

**Método crear JButtons**
```javascript
public void crearJButtons(){
  // BOTÓN INICIO--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iInicio.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bInicio = sObjGraficos.construirJButton(
      "      Inicio",
      30, 30, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bInicio.addActionListener(navegacionUsuarioComponent);
    this.pInferior.add(bInicio);

    // BOTÓN PERFIL--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iPerfil.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bPerfil = sObjGraficos.construirJButton(
      "      Perfil",
      30, 80, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bPerfil.addActionListener(navegacionUsuarioComponent);
    this.pInferior.add(bPerfil);

    // BOTÓN AMIGOS--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iAmigos.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bAmigos = sObjGraficos.construirJButton(
      "      Amigos",
      30, 130, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bAmigos.addActionListener(navegacionUsuarioComponent);
    this.pInferior.add(bAmigos);

    // BOTÓN PRODUCTOS--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iProductos.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bProductos = sObjGraficos.construirJButton(
      "      Productos",
      30, 180, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bProductos.addActionListener(navegacionUsuarioComponent);
    this.pInferior.add(bProductos);

    // BOTÓN CONFIGURACIÓN--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iConfiguracion.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bConfiguracion = sObjGraficos.construirJButton(
      "      Configuraciones",
      30, 230, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bConfiguracion.addActionListener(navegacionUsuarioComponent);
    this.pInferior.add(bConfiguracion);

    // BOTÓN CERRAR SESIÓN--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iCerrarSesion.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bCerrarSesion = sObjGraficos.construirJButton(
      "      Cerrar Sesión",
      30, 280, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bCerrarSesion.addActionListener(navegacionUsuarioComponent);
    this.pInferior.add(bCerrarSesion);
}
```

Estos botones tienen unas características peculiares:
* Todos cuentan con una imagen y ademas un texto, esto es posible y el servicio **ObjGraficosService** esta configurado para estos casos.
* Para que exista una separación visible entre el icono y el texto, este ultimo empieza con unos espacios de la forma:
<div align='center'>
    <img  src='https://i.imgur.com/4NoFZpj.png'>
    <p>Separación de texto con icono dentro del botón</p>
</div>

* El botón esta vez cuenta con una dirección hacia la izquierda para que la imágen este antes que el texto, por lo que se envía como argumento **"l"** para el parámetro **dirección**.
* Cuenta con un padding para que exista una separación entre el borde y el contenido, por lo que se utiliza el borde **bVacio**, esto no se verá reflejado aun ya que el botón no cuenta con fondo, pero en futuras sesiones cuando se hable de eventos del Mouse se resaltará la importancia del uso de este borde.
* No tienen fondo por lo que se envía como argumento un **false** para el parámetro **esSolido**.

**Llamada de métodos de creación desde el constructor:**
```javascript
//Dentro del constructor después de obtener servicios 
this.crearObjetosDecoradores();
this.crearJPanels();
this.crearJLabels();
this.crearJButtons();
```

El componente gráfico esta casi listo solo falta realizar la configuración de los eventos de acción pero esto se discutirá en la siguiente sección **Navegación y gestión de visibilidad de componentes gráficos**. Por el momento se va a incorporar en la vista principal.

### **Incorporación de componente en la Single-Page app**

Nuevamente en la clase **VistaPrincipalComponent** se declara un objeto del componente **navegacionUsuario** y como se explico en la sesión anterior, debe hacerse el llamando exclusivamente a la clase **Component**.

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

La incorporación se realiza de la misma manera que se explicó con el anterior componente gráfico, esta vez será incorporado sobre el panel **pNavegacion**:
```javascript
//Dentro del constructor
vistaPrincipalTemplate.getPNavegacion().add(
    navegacionUsuarioComponent.getNavegacionUsuarioTemplate()
);
```

Si se corre la aplicación y se abre la ventana principal se observa que se ha incorporado exitosamente el segundo componente:
<div align='center'>
    <img  src='https://i.imgur.com/tWwEgbG.png'>
    <p>Vista principal con el componente Navegación usuario agregado</p>
</div>

Sin embargo al dar click sobre cualquiera de los botónes, estos no hacen nada aun, esto se discutirá en la siguiente sección.

# Navegación y gestión de visibilidad de componentes gráficos.

Ya están incorporados los dos componentes a la ventana principal, esto da una ventaja enorme ya que cada componente tiene su propia responsabilidad y su código será mucho más entendible. Lo que se quiere hacer ahora es que cuando se oprima cualquiera de los botones del componente **navegacionUsuario** la ventana principal gestione que componentes se verán dentro del panel **pPrincipal** este proceso se conoce como **navegación** que para paginas web se conoce como **enrutamiento** ya que esta gestión de visibilidad de componentes se hace mediante rutas en el navegador, sin embargo dentro del curso se referirá a este proceso como navegación, al final es el mismo resultado lo que se quiere realizar.

La clase que se debe encargar de gestionar que es visible y que no dentro de la misma es solamente la clase **VistaPrincipalComponent**, se podría pensar que el componente **navegacionUsuario** al tener los botones se debería encargar de esta labor pero es erróneo, es la misma ventana principal la que debe hacer su propia gestión.

## Creación de componentes Gráficos

Primero se van a crear los componentes gráficos a los cuales se quiere gestionar su visibilidad en la ventana principal.

<div align='center'>
    <img  src='https://i.imgur.com/LP3Er7t.png'>
    <p>Creación de componentes para gestionar la navegación</p>
</div>

A continuación se realiza el proceso de creación de uno de estos componentes (**inicio**), los demás tendrán el mismo proceso salvo por un pequeño cambio en cada uno que se explicará más adelante:

Se inicia con la codificación de la clase **InicioComponent**:

* Como por ahora este componente no va a contar con ningún botón no es necesario que implemente ninguna interfaz aun, si en el futuro cuando se este creando este componente con mas detalle se nota que contendrá botónes o necesita eventos se realizará en ese caso la implementación, pero por ahora no:
```javascript
public class InicioComponent{
}
```
* Se un objeto de la clase **Template** correspondiente y se realiza la inyección:

**Declaración:**
```javascript
private InicioTemplate inicioTemplate;
```
**Ejemplificación:**
```javascript
// Dentro del constructor
this.inicioTemplate=  new InicioTemplate(this);
```

* Se crea el método **get** Correspondiente:
```javascript
public InicioTemplate getInicioTemplate() {
  return this.inicioTemplate;
}
```

Ahora en la clase **InicioTemplate**:
* Al igual que con los otros componentes creados, esta clase esta heredada de un **JPanel**:
```javascript
public class InicioTemplate extends JPanel{
}
```

* Se recibe la inyección como se ha explicado previamente:

**Declaración:**
```javascript
private InicioComponent inicioComponent;
```
**Recibimiento de inyección e igualación:**
```javascript
public InicioTemplate(InicioComponent inicioComponent){
    this.inicioComponent = inicioComponent;
}
```
* Se configuran las propiedades gráficas al componente:
```javascript
// Dentro del constructor
this.setSize(850, 600);
this.setBackground(Color.DARK_GRAY);
this.setLayout(null);
this.setVisible(true);
```

Como se ha explicado antes este debe tener exactamente el mismo tamaño que el panel que lo incorporará, en este caso sera el panel **pPrincipal**.

***Nota:** Para la creación de los otros componentes gráficos (amigos, configuraciones, perfil, productos) el proceso será exactamente el mismo con la diferencia del color en el **setBackground**, deben ser distintos, esto para diferenciar cada uno de los componentes.*

## Comunicación bidireccional entre componentes

Como lo que se quiere es realizar la gestión de visibilidad desde la clase **VistaPrincipalComponent** pero los botones de activación se encuentra en el componente **navegacionUsuario** debe existir una comunicación bidireccional entre ambos componentes, para lo que realizaremos una **inyección de dependencia entre componentes gráficos** tal y como se realizó con el componente **barraTitulo**.

* En la clase **NavegacionUsuarioComponent** y se va a recibir por parámetro un objeto de referencia a **VistaPrincipalComponent**:

```javascript
public NavegacionUsuarioComponent(VistaPrincipalComponent vistaPrincipalComponent) {

    ...
```

* Se declará un objeto (Atributo) de la misma referencia y se iguala al objeto recibido para que sea conocido de forma global en la clase:
```javascript
private VistaPrincipalComponent vistaPrincipalComponent;

public NavegacionUsuarioComponent(VistaPrincipalComponent vistaPrincipalComponent) {
  this.vistaPrincipalComponent = vistaPrincipalComponent;

  ...
```

* Ahora en la clase **VistaPrincipalComponent** va a salir un error en la linea en la que se ejemplifico a la clase **NavegacionUsuarioComponent** ya que este exige el envío de un parámetro por constructor de un objeto de tipo **VistaPrincipalComponent**, simplemente entre los paréntesis se coloca un **this** enviándose a si mismo como argumento:
```javascript
this.navegacionUsuarioComponent = new NavegacionUsuarioComponent(this);
```
Ya se ha creado la inyección y con esto hay comunicación bidireccional entre componentes gráficos.

Antes de continuar, como la clase **VistaPrincipalComponent** se va a encargar de la navegación, se crea un método llamado   **mostrarComponente** y recibirá por parámetro un String llamado **comando**:
```javascript
public void mostrarComponente(String comando){
}
```

## Configurando eventos en componente NavegaciónUsuario

Aprovechando que todos los botones dentro del componente tienen texto, se va a tomar su comando (texto del botón) para ser enviado a la clase **VistaPrincipalComponent** y asi gestionar la navegación. De esta forma se puede evitar también la creación de los métodos **get** dentro de la clase **NavegacionUsuarioTemplate** por cada botón.

Dentro de la clase **NavegacionUsuarioComponent** específicamente en el método implementado **ActionPerformed** se va a enviar el comando del botón a la vista principal:

```javascript
@Override
public void actionPerformed(ActionEvent e) {
  this.vistaPrincipalComponent.mostrarComponente(e.getActionCommand());
}
```

Del anterior código se pueden notar varias cosas:
* Como la gestión de navegación se hace desde la vista principal no es necesario realizar una discriminación de acción desde aquí, esto se realizara en la clase **VistaPrincipalComponent**.
* Es posible notar aquí la importancia de declarar un objeto inyectado para igualarlo dentro del constructor, si esto no se hiciera el objeto inyectado solo existiría dentro del constructor y cuando se intente llamar al método  **mostrarComponente** desde el método **actionPerformed** sacaría un error en ejecución ya que para este entorno no existiría el objeto **vistaPrincipalComponent**.
* Recordar que el método **getAtionCommand()** va a retornar el texto que contiene el botón que activo el evento (el que se oprimió) en forma de String asi que puede enviarse sin problema como argumento al método **mostrarComponente**. 

Sin embargo, como se vió anteriormente, para hacer la separación del texto con el icono en cada botón el texto iniciaba con unos espaciós, para probar esto, se realiza una muestra por consola asi:
```javascript
@Override
public void actionPerformed(ActionEvent e) {
    System.out.println(e.getActionCommand());
    this.vistaPrincipalComponent.mostrarComponente(e.getActionCommand());
}
```
Cuando se ejecuta el programa y se oprime cualquier botón del componente **navegacionUsuario** y se observa la consola se puede notar lo siguiente:

<div align='center'>
    <img  src='https://i.imgur.com/noESoYT.png'>
    <p>Texto de cada botón con espacios al inicio</p>
</div>

No se quieren enviar esos espacios que tiene cada botón en su texto por que no se tiene la certeza de cuantos espacios son o si son los mismos en cada botón, ademas contarlos seria un desperdicio de tiempo.

Se va a hacer uso del método **trim** este método:
* **trim()**: Quita todos los espacios que existan antes y al finalizar un texto dentro de un String.
la configuración queda asi:

```javascript
@Override
public void actionPerformed(ActionEvent e) {
    System.out.println(e.getActionCommand().trim());
    this.vistaPrincipalComponent.mostrarComponente(e.getActionCommand().trim());
}
```

Una vez se ejecuta la aplicación y se ve la consola se puede notar lo siguiente:
<div align='center'>
    <img  src='https://i.imgur.com/zIrf2vz.png'>
    <p>Texto de cada botón sin espacios al inicio</p>
</div>

Se puede observar que incluso si existe espacio entre el texto este se conserva como es el caso del comando **cerrar Sesión**.

***Nota:** El método para mostrar por consola **(System.out.println)** se uso como una prueba, asi que se puede retirar.*

## Configuración de la navegación

Ya esta casi todo listo para configurar la navegación, ahora se codificará en el método **mostrarComponente** de la clase **VistaPrincipalComponent**.
Una vez se recibe el comando del botón desde el componente **navegacionUsuario** se puede llamar a los demás componentes de acuerdo a la petición del usuario. Esto se realiza con un **switch / case** de la siguiente forma:

```javascript
public void mostrarComponente(String comando){
    switch(comando){
        case "Inicio":
            break;
        case "Perfil":
            break;
        case "Amigos":
            break;
        case "Productos":
            break;
        case "Configuraciones":
            break;
        case "Cerrar Sesión":
            break;
    }
}
```

Note que cada caso dentro del **switch** corresponde al comando de cada botón. Ahora dentro de cada caso se va a realizar la incorporación de cada uno de los componentes de acuerdo a la petición. Se realiza el ejemplo para el componente **inicio**, sin embargo para el resto de los componentes es igual:

***Nota:** La opción cerrar sesión tendrá un tratamiento diferente y se discutirá de esto en la sección final **Control en la creación de componentes gráficos en memoria** por ahora se deja vacía.*

* Primero se obtiene el panel que va a incorporar al componente gráfico desde la clase **VistaPrincipalTemplate** con su método **get** correspondiente, en este caso se llama al panel **pPrincipal**:
```javascript
case "Inicio":
    vistaPrincipalTemplate.getPPrincipal();
    break;
```

* Se le indica al panel que va a agregar un Objeto gráfico:
```javascript
case "Inicio":
    vistaPrincipalTemplate.getPPrincipal().add();
    break;
```

* Ahora se debe indicar al panel que componente será agregado, para esto es posible realizar una **ejemplificación anónima del componente** en cuestión. Para este caso será el componente gráfico **inicio**, se realiza entonces la ejemplificación de la clase **InicioComponent**:

```javascript
case "Inicio":
    vistaPrincipalTemplate.getPPrincipal().add(new InicioComponent());
    break;
```

* Sin embargo como se sabe, la clase **Component** no cuenta con características gráficas por lo que se debe llamar a su clase **Template** correspondiente, se hace a traves del método **get** de esta:

```javascript
case "Inicio":
    vistaPrincipalTemplate.getPPrincipal().add(
        new InicioComponent().getInicioTemplate()
    );
    break;
```
Se re acomoda el código para no ocupar mucho espacio horizontal.

Esto se realiza con los demás componentes y en teoría estaría listo, sin embargo, si se ejecuta la aplicación y se oprimen los botones se puede notar que este no realiza ningún cambio aparente.

**¿Por qué sucede esto?**

Hacen falta un par de configuraciones adicionales:
* Para empezar cada vez que se vuelva a llamar un nuevo componente para ser incorporado en el panel **pPrincipal** es necesario que antes de la incorporación se remueva todo lo que este panel contiene, para que esto sea posible se debe llamar al método **removeAll()**. Esto debe hacerse justamente antes de que empiece el switch:

```javascript
public void mostrarComponente(String comando){
    vistaPrincipalTemplate.getPPrincipal().removeAll();
    switch(comando){
        ...
        ...
    }
```

* Cada vez que un componente sea agregado en el panel **pPrincipal** se debe actualizar toda la ventana para que esta esta pueda mostrar en la pantalla los cambios ocurridos. Para esto se debe llamar al método **repaint()**. Como este método se realiza una vez se haya incorporado el componente en el panel se debe escribir justo debajo del switch:

```javascript
public void mostrarComponente(String comando){
    vistaPrincipalTemplate.getPPrincipal().removeAll();
    switch(comando){
        ...
        ...
    }
    vistaPrincipalTemplate.repaint();
}
```

Si se la aplicación y se oprimen los botones de la navegación se puede observar que ya reemplaza los componentes en el panel principal:

<div align='center'>
    <img  src='https://i.imgur.com/2F1rXG7.png'>
    <p>VentanaPrincipal una vez se oprimió el botón Inicio</p>
</div>

<div align='center'>
    <img  src='https://i.imgur.com/0n7YhBR.png'>
    <p>VentanaPrincipal una vez se oprimió el botón Configuraciones</p>
</div>

# Control en la creación de componentes gráficos en memoria

Cuando se oprima el botón **Cerrar sesión** se quiere que la vista principal deje de ser visible y pueda verse de nuevo el Login. Una opción simple para esto puede ser declarar un objeto de tipo **LoginComponent**  desde la clase **VistaPrincipalComponent**, ejemplificarla y decirle a la clase **VistaPrincipalTemplate** que deje de ser visible:

<div align='center'>
    <img  src='https://i.imgur.com/zzIRQv7.png'>
    <p>Posible caso de regreso al Login</p>
</div>

El anterior ejemplo funciona, sin embargo, es necesario recordar que cuando se inicio la aplicación la clase ejecutora **App** ya creo un objeto en memoria del componente **login**, y si se realiza el proceso anterior descrito se estaría creando otro objeto en memoria nuevo del componente **login** cada vez que se cierre sesión, ´por ende el objeto que se creo desde **App** quedaría en el *limbo*.

De hecho si se hecha un vistazo a la clase **LoginComponent** en su método **Entrar** se observa que cada vez que se entra a la ventana principal crea un nuevo objeto de esta:

<div align='center'>
    <img  src='https://i.imgur.com/LcNe8DZ.png'>
    <p>Creación de un nuevo objeto cada vez que se entra a la aplicación</p>
</div>

Esto es un problema enorme, imagine que un usuario entra y cierra sesión 10 veces, en memoria se estarían creando 10 objetos tanto del componente  **login** como de **VistaPrincipal**, incluso cuando se habla de objetos en memoria de componentes gráficos en realidad se hace alusión a todas las clases que conforman un componente por lo que el problema se extiende a medida que se entra en mas detalle. Este problema debe ser arreglado.

Para empezar se va a hacer una **inyección de dependencia entre componentes** esta vez no con el propósito de crear una comunicación bidireccional, lo importante de esta inyección es garantizar que solo existirá un objeto en memoria para el componente de login y uno solo para el componente de la vista principal. **Esto no significa que siempre que se quiera controlar la creación de objetos de algún componente se deba realizar inyección de dependencia**, en este caso se hace por que desde el login se va a gestionar la visibilidad de la ventana principal una vez se inicie sesión y desde la ventana principal se va a gestionar la visibilidad del login una vez se cierre sesión y para eso es necesaria una comunicación bidireccional. 

Como el programa inicia con el login la inyección se realizará desde la clase **LoginComponent** a la clase **VistaPrincipalComponent**:

* En la clase **LoginComponent**, específicamente en el método **entrar**, cuando se ejemplifica la clase **VistaPrincipalComponent**, se pasa ahora como argumento el **this** para mandar el objeto de esta clase inyectado:
```javascript
this.vistaPrincipal = new VistaPrincipalComponent(this);
```

* En la clase **VistaPrincipalComponent**, ahora se va a recibir por parámetro un objeto de la clase **LoginComponent** y se iguala a un objeto (atributo) declarado del mismo:

```javascript
private LoginComponent loginComponent;

public VistaPrincipalComponent(LoginComponent loginComponent){
    this.loginComponent = loginComponent;
    ...
}
```

La inyección ya esta hecha y ahora se tiene una comunicación bidireccional entre ambos componentes gráficos, sin embargo, aun no se ha evitado la creación de muchos objetos del componente **VistaPrincipal** para esto dentro del método **entrar** de la clase **LoginComponent** se realiza el siguiente cambio:
```javascript
public void entrar(){
    if(vistaPrincipal == null)
        this.vistaPrincipal = new VistaPrincipalComponent(this);
    else
        this.vistaPrincipal.getVistaPrincipalTemplate().setVisible(true);
    loginTemplate.setVisible(false);
}
```

En el anterior código se realiza lo siguiente:
* Se pregunta si el objeto de la clase **VistaPrincipalComponent** no se ha ejemplificado, si aun no se ha entrado a la vista principal este objeto efectivamente estará vacío ya que no se ha ejemplificado antes.
    * Si este esta vacío se ejemplifica enviando como argumento una referencia de la clase **LoginComponent** con un **this** y asi realizar la inyección.
    * Si este ya se ha ejemplificado previamente (por ejemplo se inicio sesión una vez, se cerro la sesión y se volvió a iniciar) entonces se obtiene la clase **VistaPrincipalTemplate** mediante el método **get** y se le indica que sea Visible nuevamente.
* Para ambos casos la visibilidad del Login cambiara para que no se vea en pantalla.

Ya se ha arreglado una parte del problema, ahora solo queda configurar finalmente la opción de cerrar sesión. Dentro del método **mostrarComponente** de la clase **VistaPrincipalComponent** en la opción **Cerrar Sesión** se codifica:

```javascript
case "Cerrar Sesión":
    this.loginComponent.getLoginTemplate().setVisible(true);
    this.vistaPrincipalTemplate.setVisible(false);
    break;
```

De esta manera se ha controlado la forma de iniciar y cerrar sesión gestionando correctamente la creación de objetos en los componentes gráficos.

Ahora si se observa a las demás opciónes de navegación se puede notar nuevamente que cada vez que se oprime cualquier botón que incorpora los componentes gráficos que se muestran en el panel **pPrincipal** se esta creando un objeto nuevo de estos. Esto es el mismo problema que acabamos de tratar. 

<div align='center'>
    <img  src='https://i.imgur.com/8csTZXS.png'>
    <p>Problema en creación descontrolada de objetos de los componentes gráficos</p>
</div>

Para corregir esto, una alternativa puede ser la de **declarar** los objetos de los componentes, **ejemplificarlos** en el constructor e **incorporar** ese objeto en las opciones de navegación: 

***Nota:** Se realizara el proceso solo con el componente gráfico **inicio** pero será igual para los demás componentes*.

**Declaración:**
```javascript
// Al inicio de la clase VistaPrincipalComponent
private InicioComponent inicioComponent;
```

**Ejemplificación:**
```javascript
// Dentro del constructor
this.inicioComponent = new InicioComponent();
```

**Incorporación:**
```javascript
// Dentro del método mostrarComponente
case "Inicio":
    vistaPrincipalTemplate.getPPrincipal().add(
        inicioComponent.getInicioTemplate()
    );
    break;
```

Es posible notar que este enfoque funciona y se tiene de forma controlada la creación de sus componentes, sin embargo, como todos los componentes gráficos se van a cargar desde el constructor esto le va a restar rendimiento a la aplicación, imaginen que algún usuario ingresa solamente a revisar los productos y nunca oprime el botón de configuración o amigos, se habrá cargado todo el componente de configuraciones o amigos en vano y gastará memoria y rendimiento.

Una mejor alternativa es la que se uso en la clase **LoginComponent** donde con un condicional se controla la ejemplificación del objeto asi: 

```javascript
case "Inicio":
    if (this.inicioComponent == null)
        this.inicioComponent = new InicioComponent();
    vistaPrincipalTemplate.getPPrincipal().add(
        inicioComponent.getInicioTemplate()
    );
    break;
```
***Nota:** Como se esta ejemplificando ahora desde el método mostrarComponente se debe retirar la ejemplificación que se realizó en el constructor*.

De esta forma la primera vez que se oprima el botón inicio se creara el objeto en memoria y se incorporara en la ventana principal, pero cuando se vuelva a oprimir simplemente incorporará el objeto que previamente se ejemplifico. Ademas de controlar la cantidad de objetos también lo se crean solamente en caso de ser necesario y de esta forma se gana también en el rendimiento de la aplicación.

# Resultado
Si has llegado hasta aquí **!felicitaciones!** se ha aprendido como incorporar componentes gráficos a la ventana principal para crear un Single-Page App. Se aprendió también como realizar navegación para gestionar la visibilidad de los componentes dentro de la ventana principal. Ademas se ha corregido la creación masiva de objetos de los componentes cuando se quiere gestionar su visibilidad.

# Actividad

Realizar la incorporación de componentes gráficos sobre la ventana principal de su proyecto y realizar navegación de tal forma que se controle la creación de objetos en memoria de los componentes.
