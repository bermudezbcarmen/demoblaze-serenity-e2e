# DEMOBLAZE E2E AUTOMATION TEST

## 1. OBJETIVO

Automatizar mediante Serenity BDD, Screenplay y Playwright el flujo E2E de compra en [DemoBlaze](https://www.demoblaze.com/).

El flujo automatizado:

- Agrega dos productos al carrito.
- Visualiza el carrito.
- Valida los productos agregados.
- Completa el formulario de compra.
- Finaliza la compra.
- Valida el mensaje de confirmación.
- Cierra la confirmación y verifica el regreso al catálogo.

## 2. TECNOLOGÍAS

- Java 17 (compilación con `release 17`).
- Maven.
- Serenity BDD 5.3.7.
- Screenplay Pattern.
- Playwright 1.58.0.
- JUnit Jupiter 6.0.3, según `pom.xml`, con la extensión `SerenityJUnit5Extension`.

## 3. PRERREQUISITOS

- JDK 17 o superior, con `JAVA_HOME` configurado.
- Maven 3.8 o superior. Las ejecuciones del proyecto se validaron con Maven 3.9.8.
- Git.
- Conexión a internet para descargar dependencias y acceder a DemoBlaze.
- Navegadores de Playwright instalados mediante el comando de la sección 5.

## 4. CLONAR EL PROYECTO

```powershell
git clone https://github.com/bermudezbcarmen/demoblaze-serenity-e2e.git
cd demoblaze-serenity-e2e
```

## 5. INSTALAR BROWSERS DE PLAYWRIGHT

Ejecutar desde la raíz del proyecto. En PowerShell, las comillas conservan cada propiedad como un único argumento. El alcance `test` permite acceder a la dependencia de Playwright.

```powershell
mvn exec:java -e "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.classpathScope=test" "-Dexec.args=install"
```

## 6. EJECUTAR PRUEBAS

```powershell
mvn clean verify
```

La configuración actual ejecuta Chromium con ventana visible y requiere una sesión de escritorio. Failsafe ejecuta `PurchaseProductsE2EIT` una sola vez y Serenity genera el reporte. `mvn test` no ejecuta este escenario E2E.

## 7. EJECUCIÓN HEADLESS

Para ejecutar sin mostrar la ventana del navegador:

```powershell
mvn clean verify "-Dplaywright.headless=true"
```

## 8. REPORTE

Después de ejecutar las pruebas, abrir:

```text
target/site/serenity/index.html
```

Los resultados de Failsafe se encuentran en `target/failsafe-reports`.

Actualmente, `evidence/index.html` contiene únicamente una copia del HTML principal, sin los recursos necesarios para consultar el reporte completo. No se incluye una copia completa en `evidence/serenity-report/index.html`. Para compartir el reporte navegable, se debe conservar toda la carpeta `target/site/serenity`, incluidos sus recursos.

## 9. ESTRUCTURA

Dentro de `src/test/java/com/demoblaze`:

| Carpeta | Contenido |
| --- | --- |
| `ui` | Elementos de interfaz utilizados por las pruebas. |
| `tasks` | Acciones de negocio del actor y validaciones de páginas y modales. |
| `models` | Estructuras de datos utilizadas por las pruebas. |
| `tests` | Escenarios E2E. |

La configuración está en `src/test/resources/serenity.properties` y las dependencias y plugins en `pom.xml`.

## 10. ESCENARIO AUTOMATIZADO

Escenario: `PurchaseProductsE2EIT.shouldPurchaseTwoProductsSuccessfully`.

1. Abrir DemoBlaze y validar que el catálogo esté disponible.
2. Comprobar que el carrito inicial esté vacío y regresar al catálogo.
3. Seleccionar Samsung galaxy s6, validar su página y agregarlo al carrito.
4. Esperar la confirmación del agregado y volver al catálogo.
5. Seleccionar Nokia lumia 1520, validar su página y agregarlo al carrito.
6. Esperar la confirmación del agregado y volver al catálogo.
7. Abrir el carrito y validar la página.
8. Validar que ambos productos estén visibles.
9. Seleccionar Place Order y validar el formulario.
10. Completar nombre, país, ciudad, tarjeta de prueba, mes y año.
11. Comprar y validar el mensaje `Thank you for your purchase!` y el botón OK.
12. Pulsar OK, comprobar que desaparezca la confirmación y validar el regreso al catálogo.

Las validaciones de navegación comprueban la URL y los elementos distintivos de cada página; los modales se validan mediante sus elementos visibles y controles disponibles. Se utilizan esperas automáticas, sin pausas fijas.
