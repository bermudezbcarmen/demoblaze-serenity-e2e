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

## 3. CLONAR EL PROYECTO

```powershell
git clone https://github.com/bermudezbcarmen/demoblaze-serenity-e2e.git
cd demoblaze-serenity-e2e
```

## 4. INSTALAR BROWSERS DE PLAYWRIGHT

Ejecutar desde la raíz del proyecto. 

```powershell
mvn exec:java -e "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.classpathScope=test" "-Dexec.args=install"
```

## 5. EJECUTAR PRUEBAS

```powershell
mvn clean verify
```

## 6. REPORTE

Después de ejecutar las pruebas, abrir:

```text
target/site/serenity/index.html
```
