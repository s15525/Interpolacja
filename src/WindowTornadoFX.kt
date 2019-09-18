import tornadofx.*

/**
 * Created by Łukasz Tołpa on 12.09.2019.
 */
class WindowApp : App(WindowTornadoFX::class)

class WindowTornadoFX : View("My View") {
    override val root = borderpane {
        form{
                center = imageview("file:\\C:\\Users\\Public\\Interpolacja.jpg",true)
        }
    }
}
