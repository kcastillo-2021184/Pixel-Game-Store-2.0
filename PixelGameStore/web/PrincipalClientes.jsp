<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Principal</title>  
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css " integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="PrincipalCSS.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav">
                <li class="nav-item ">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="">Home</a>
                </li>
                <li class="nav-item">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="" target="myFrame">Tienda</a>
                </li>
              </ul>
            </div>
            <div class="dropdown">
                <div class="dropdown-menu text-center" aria-labelledby="dropdownMenu2">
                  <div class="dropdown-divider"></div>
                  <form action="Validar" method="POST">
                      <button name="accion" name="Salir" class="dropdown-item">Salir</button>
                  </form>
                </div>
              </div>         
          </nav>
            
            <section class="header">
            <div class="text-box">
                <h1>PIXEL GAME STORE</h1>
                <p>!Bienvenido a Pixel Game Store! <br>Aquí, donde los píxeles cobran vida y los periféricos se vuelven tus mejores aliados, <br> encontrarás todo lo que necesitas para llevar tu experiencia de juego al siguiente nivel. </p>
                <div style="width: 50%; margin: 0 auto;">
                <div class="brick one"></div>
                <div class="tooltip-mario-container">
                  <div class="box"></div>
                  <div class="mush">
                    <svg
                      class="icon"
                      viewBox="0 0 1024 1024"
                      xmlns="http://www.w3.org/2000/svg"
                      width="48"
                      height="48"
                    >
                      <path
                        d="M288.582 111.71h55.854v55.854h-55.854v-55.855zm-111.71 484.072h167.564v55.854H176.873v-55.854zM623.71 502.69h111.71v55.854h-111.71v-55.854zm55.855 55.854h111.709V614.4h-111.71v-55.855zm0 55.855h167.563v37.236H679.564V614.4z"
                        fill="#B8332B"
                      ></path>
                      <path
                        d="M176.873 651.636h167.563v74.473H176.873v-74.473zm0 74.473h111.709v74.473h-111.71v-74.473zm558.545 0h111.71v74.473h-111.71v-74.473zm-55.854-74.473h167.563v74.473H679.564v-74.473zm-316.51-93.09h55.855V614.4h-55.854v-55.855zm204.8 0h55.855V614.4h-55.854v-55.855z"
                        fill="#FFF1E3"
                      ></path>
                      <path
                        d="M791.273 595.782h55.854V614.4h-55.854v-18.618zm-55.855-55.855h37.237v18.618h-37.237v-18.618zm-316.509-93.09h204.8v111.708h-204.8V446.836zM232.727 558.544h111.71v37.237h-111.71v-37.237zm111.71-111.709h18.618v37.237h-18.619v-37.237zM307.2 484.073h55.855v18.618H307.2v-18.618zm-18.618 18.618h74.473v37.236h-74.473v-37.236zm-37.237 37.236h111.71v18.618h-111.71v-18.618zM623.71 111.71h18.618v55.855H623.71v-55.855zm18.618 37.236h148.946v18.619H642.327v-18.619zm-297.89-55.854h279.272v74.473H344.436V93.09z"
                        fill="#B8332B"
                      ></path>
                      <path
                        d="M344.436 55.855H623.71V93.09H344.436V55.855zm297.891 55.854h148.946v37.236H642.327V111.71zM288.582 446.836h55.854v37.237h-55.854v-37.237zm446.836 55.855h55.855v37.236h-55.855v-37.236zm55.855 55.854h55.854v37.237h-55.854v-37.237zm-502.691-74.472H307.2v18.618h-18.618v-18.618zm484.073 55.854h18.618v18.618h-18.618v-18.618zm-539.928 0h18.618v18.618h-18.618v-18.618zm0-37.236h55.855v37.236h-55.855v-37.236zm-55.854 55.854h55.854v37.237h-55.854v-37.237z"
                        fill="#FF655B"
                      ></path>
                      <path
                        d="M288.582 167.564h55.854v55.854h-55.854v-55.854zm0 167.563h55.854v55.855h-55.854v-55.855z"
                        fill="#432E23"
                      ></path>
                      <path
                        d="M269.964 856.436h148.945v55.855H269.964v-55.855zm0 55.855h148.945v55.854H269.964v-55.854z"
                        fill="#9F5A31"
                      ></path>
                      <path
                        d="M176.873 912.29h93.09v37.237h-93.09v-37.236zm577.163 0h93.091v37.237h-93.09v-37.236z"
                        fill="#F38C50"
                      ></path>
                      <path
                        d="M176.873 949.527h93.09v18.618h-93.09v-18.618zm577.163 0h93.091v18.618h-93.09v-18.618zm-148.945-93.09h148.945v55.854H605.091v-55.855zm0 55.854h148.945v55.854H605.091v-55.854z"
                        fill="#9F5A31"
                      ></path>
                      <path
                        d="M363.055 446.836h55.854v111.71h-55.854v-111.71zm0 167.564h316.509v37.236h-316.51V614.4zm-18.619 37.236h335.128v74.473H344.436v-74.473zm-55.854 74.473h446.836v74.473H288.582v-74.473zm130.327-130.327h148.946V614.4H418.909v-18.618zm-130.327 204.8h167.563v55.854H288.582v-55.854zm279.273 0h167.563v55.854H567.855v-55.854zm55.854-242.037h55.855V614.4h-55.855v-55.855z"
                        fill="#2E67B1"
                      ></path>
                      <path
                        d="M418.91 558.545h148.945v37.237H418.909v-37.237z"
                        fill="#66A8FF"
                      ></path>
                      <path
                        d="M344.436 558.545h18.619v93.091h-18.619v-93.09z"
                        fill="#2E67B1"
                      ></path>
                      <path
                        d="M400.29 279.273h55.855v55.854h-55.854v-55.854zm0-111.71h55.855v55.855h-55.854v-55.854zm-55.854 0h55.855v167.564h-55.855V167.564zm279.273 111.71h55.855v55.854h-55.855v-55.854zm-55.854-55.855h55.854v55.855h-55.854v-55.855zm0 111.71h223.418v55.854H567.855v-55.855z"
                        fill="#432E23"
                      ></path>
                      <path
                        d="M288.582 223.418h55.854v111.71h-55.854v-111.71zm167.563-55.854h223.419v55.854H456.145v-55.854zm-55.854 55.854h167.564v55.855H400.29v-55.855zm55.854 55.855H623.71v55.854H456.145v-55.854zm-111.709 55.854h223.419v55.855H344.436v-55.855zm0 55.855h390.982v55.854H344.436v-55.854zM623.71 223.418h167.564v55.855H623.709v-55.855zm55.855 55.855h167.563v55.854H679.564v-55.854z"
                        fill="#FFF1E3"
                      ></path>
                      <path
                        d="M232.727 223.418h55.855v167.564h-55.855V223.418z"
                        fill="#432E23"
                      ></path>
                      <path
                        d="M232.727 111.71h55.855v111.708h-55.855V111.71zm-55.854 111.708h55.854v167.564h-55.854V223.418zm55.854 167.564h111.71v55.854h-111.71v-55.854zm-55.854 409.6h111.709v55.854h-111.71v-55.854zm279.272 0h111.71v55.854h-111.71v-55.854zm-279.272 55.854h93.09v55.855h-93.09v-55.855zm-55.855 55.855h55.855V1024h-55.855V912.29zm726.11 0h55.854V1024h-55.855V912.29zm-670.255 55.854H400.29V1024H176.873v-55.855zm446.836 0h223.418V1024H623.71v-55.855zm111.71-167.563h111.708v55.854H735.418v-55.854zm18.617 55.854h93.091v55.855h-93.09v-55.855zM288.582 55.855h55.854v55.854h-55.854V55.855zm-55.855 390.981h55.855v55.855h-55.855v-55.855zm-55.854 55.855h55.854v55.854h-55.854v-55.854zm614.4 0h55.854v55.854h-55.854v-55.854zm-670.255 55.854h55.855v242.037h-55.855V558.545zM418.91 856.436h55.855v111.71h-55.855v-111.71zm130.327 0h55.855v111.71h-55.855v-111.71zm297.891-297.89h55.855v242.036h-55.855V558.545zm-55.854-446.837h55.854v55.855h-55.854v-55.855zm0 111.71h55.854v55.854h-55.854v-55.855zm55.854 55.854h55.855v55.854h-55.855v-55.854zm-55.854 55.854h55.854v55.855h-55.854v-55.855zm-55.855 55.855h55.855v55.854h-55.855v-55.854zM623.71 446.836h167.564v55.855H623.709v-55.855zm0-390.981h167.564v55.854H623.709V55.855zm55.855 111.709h111.709v55.854h-111.71v-55.854zM344.436 0H623.71v55.855H344.436V0z"
                        fill="#10001D"
                      ></path>
                    </svg>
                  </div>
                </div>
                <div class="brick two"></div>
            </div>
            </div>
            

        </section>
       
        <section class="definicion">
             <br> 
            <h1>¿Quiénes somos?</h1>
            <p>Bienvenidos a nuestra empresa Pixel-Game-Store la cuál esta orientada a la venta de videojuegos, consolas y periféricos gaming tratando de llevarlas <br>a todo tipo de publico desde personas que es la primera vez que prueban una consola y les interesa todo este mundo, hasta los más gamers de la ciudad.<br> Nuestra variedad de videojuegos y consolas es de todo tipo desde consolas y juegos de la vieja escuela o retros<br> hasta las consolas más actualizadas y nuevas del mercado, contando también con los juegos más actualizados y nuevos del mercado </p>
        </section>
        <section class="beneficios">
            <div class="beneficios-lista">
                <div class="columna-lista">
                    <h3>Misión</h3>
                    <p>En Pixel-Game-Store buscamos ofrecer una amplia variedad y gama de videojuegos y consolas de última generación, 
                        brindando a nuestros clientes un servicio excepcional y una experiencia de compra incomparable. Nos esforzamos por ser la 
                        tienda preferida para los entusiastas de los videojuegos de todas las edades desde los mas pequeños del hogar hasta los más grandes, 
                        asegurando disponibilidad, calidad y precios competitivos en todos nuestros productos.
                    </p>
                </div>
                <div class="columna-lista">
                    <h3>Visión</h3>
                    <p>En Pixel-Game-Store, queremos ser la tienda de videojuegos líder en Guatemala, destacándonos por la calidad de nuestros productos 
                        y un excelente servicio al cliente. Ofrecemos una amplia gama de consolas y juegos, desde los más recientes hasta los retro, tanto en 
                        nuestras tiendas físicas como en línea. Buscamos crear una comunidad de gamers y brindar una experiencia de compra personalizada e innovadora.
                    </p>
                </div>
                <div class="columna-lista">
                    <h3>Nuevo Propósito</h3>
                    <p>En Pixel-Game-Store, nuestro propósito es acercar el entretenimiento digital a cada hogar, promoviendo la diversion en familia y personal, De la 
                        misma forma la pasión por los videojuegos. Nos comprometemos a apoyar la diversidad en el mundo de los videojuegos, ofreciendo productos 
                        que puedan ser disfrutados por todos, Sin importar de su edad, género o habilidad.
                    </p>
                </div>
            </div>
        </section>
        <section class="ejemplos">
            <h1><strong>Algunas Marcas Que Manejamos</strong></h1>
            <br>
            <br>
            <br>

            <div class="ejemplos-row">
                <div class="col-maquinas">
                    <img src="img/nintendoLogo.png">
                    <div class="layer">
                       
                    </div>
                </div>
                <div class="col-maquinas">
                    <img src="img/asusLogo.jpg">
                    <div class="layer">
                       
                    </div>
                </div>
                <div class="col-maquinas">
                    <img src="img/psLogo.png">
                    <div class="layer">
                        
                    </div>
                </div>
            </div>
            <br>
            <br>
            <br>
        </section>
        <section class="final">
            <div class="final-box">
                <h1>¿Listo para iniciar el juego?</h1>
                <p>Da el siguiente paso y entra a nuestra tienda online para 
                    comprar los mejores productos de videojuegos al mejor precio</p>
                <button class="btnA" href="">
                    Listo
                  </button>
            </div>
            <main id="container">

                <div class="dots">
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                    <div class="dot"></div>
                </div>
                <div class="dots2">
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                    <div class="dot2"></div>
                </div>
                <div class="circle"></div>
            </main>
            <br>
        </section>
        <footer class="footer">
            <p>&copy; 2024 - Pixel Game Store. Todos los derechos reservados.</p>
        </footer>
        
        
                  <div class="m-4" style="height: 850px">
                      <iframe name="myFrame" style="height:100%; width:100%;border:none"></iframe>
                  </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>



