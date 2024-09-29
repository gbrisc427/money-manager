# Money-Manager

## Descripción
**Money-Manager** es una aplicación diseñada para gestionar cuentas y transacciones financieras de manera simple y eficiente. La interfaz gráfica permite al usuario interactuar con diferentes secciones, como la creación de cuentas, la visualización de saldos, y la gestión de movimientos financieros. La aplicación está completamente desarrollada en **Java**, usando principios de **Programación Orientada a Objetos (POO)** y emplea el **Modelo de Tres Capas** para una mayor modularidad y mantenimiento del código.

## Características Principales
- **Gestión de cuentas:** Permite crear, modificar, y consultar diferentes cuentas con facilidad.
- **Transacciones:** Facilita la creación y seguimiento de movimientos financieros entre cuentas.
- **Interfaz gráfica interactiva:** Utiliza una interfaz basada en ventanas que permite al usuario gestionar sus cuentas de manera intuitiva.
- **Soporte multi-cuentas:** El usuario puede crear y gestionar múltiples cuentas de manera simultánea.
- **Interfaz personalizada:** Personalización de botones, paneles y componentes mediante `UIManager` para un diseño único.

## Tecnologías Utilizadas
- **Lenguaje:** Java
- **Arquitectura:** Modelo de Tres Capas (Capa de Presentación, Capa de Negocios y Capa de Datos).
  - **Capa de Presentación:** Gestiona toda la interfaz gráfica de la aplicación utilizando `JFrame`, `JPanel`, `JButton`, `JTextField`, entre otros.
  - **Capa de Negocios:** Contiene toda la lógica de negocio, como la validación de transacciones y cálculos de saldos.
  - **Capa de Datos:** Se encarga de la persistencia de la información y el acceso a datos de las cuentas. Se utiliza un sistema de CSVs

## Detalles Técnicos
- **Orientación a Objetos:** Cada componente y funcionalidad del sistema está encapsulado en clases bien definidas, facilitando la escalabilidad y el mantenimiento de la aplicación.
- **Gestión de Eventos:** Uso de `ActionListener`, `ItemListener`, y otros manejadores de eventos para controlar la interacción del usuario con la interfaz gráfica.
- **Layouts y Gestión de Componentes:** Se utilizan diferentes gestores de diseño (`FlowLayout`, `GridBagLayout`, `BorderLayout`, etc.) para estructurar la disposición de los componentes en las ventanas.
- **Personalización mediante `UIManager`:** Personalización avanzada de elementos de la interfaz como botones, etiquetas, checkboxes, combo boxes, y barras de desplazamiento.

## Requisitos del Sistema
- **Java 8** o superior.
- **IDE recomendado:** IntelliJ IDEA  para el desarrollo y ejecución de la aplicación.

## Licencia
- Este proyecto está licenciado bajo la licencia MIT - ver el archivo LICENSE para más detalles.

