# Human Resources Management Server

## Overview
El Servidor de Gestión de Recursos Humanos es una aplicación de servidor diseñada para gestionar y mantener información relacionada con empleados, puestos de trabajo, historiales laborales, ubicaciones geográficas y más en una empresa u organización. Este servidor proporciona una interfaz de programación (API) para interactuar con la base de datos y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en los diferentes recursos.

## Requisitos
- Java 11
- Base de datos MySQL
- Maven

## Setup and Installation
1. Clone el repositorio en su máquina local:
```bash
git clone https://github.com/[user-name]/human_resources_managment_client.git
```
2. Navegue al directorio del proyecto clonado.
3. Configura la base de datos MySQL y actualiza la información de conexión en el archivo `application.properties`.
4. Compile y empaquete la aplicación usando Maven:
```bash
mvn package
```
4. Ejecute la aplicación:
```bash
java -jar target/[nombre-del-archivo-jar].jar
```

## Usage
Utiliza la API proporcionada para interactuar con el servidor y gestionar la información de recursos humanos de tu organización. Asegúrate de consultar la documentación de la API para obtener detalles sobre los puntos de entrada disponibles y cómo utilizarlos.

## Estructura del Proyecto
- `src/main/java/com/poli/human_resources_managment_server`: Contiene los archivos fuente Java de la aplicación, incluyendo los controladores, modelos y DAOs.
- `src/main/resources`: Contiene archivos de recursos, incluyendo el archivo de configuración application.properties.
- `pom.xml`: Archivo de configuración Maven que especifica las dependencias y configuraciones del proyecto.
