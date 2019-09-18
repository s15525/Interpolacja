import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Created by Łukasz Tołpa on 10.09.2019.
 */
//val fileImput = File("C:\\Users\\Łukasz Tołpa\\Desktop\\Interpolacja\\Source\\simple.png")
val fileImput = File("C:\\Users\\Łukasz Tołpa\\Desktop\\Interpolacja\\Source\\_DSC1693.JPG")
val netting = ImageIO.read(fileImput)

class Image(pathImage: String) {

    init {
        val fileresult = File("result.JPG")
        nettingPhoto(fileresult)
    }

    fun nettingPhoto(file: File) {
        val buffedImage = BufferedImage(netting.width * 2, netting.height * 2, BufferedImage.TYPE_INT_RGB)
        var tabPom: Array<Int> = Array(4, { 0;0;0;0 }) // X;Y;CentrY;Counter
        for (x in 0 until netting.width) {
            for (y in 0 until netting.height) {
                tabPom = paintInterpolarPixel(buffedImage, x, y, netting.getRGB(x, y), tabPom)
//                print("X: $x Y: $y")
//                println()
//                for (x in 3 until tabPom.size){
//                    print("X: " + tabPom[x-3] + " Y: " + tabPom[x-2] + " CentrY: " + tabPom[x-1] + " Counter " + tabPom[x])
//                    println()
// }
            }
        }
        ImageIO.write(buffedImage, "JPG", file)
    }

    fun paintInterpolarPixel(bufferedImage: BufferedImage, x: Int, y: Int, color: Int, tabPom: Array<Int>): Array<Int> {

        if (x == 0 && y == 0) {
            bufferedImage.setRGB(x, y, color)
            bufferedImage.setRGB(x + 1, y, color)
            bufferedImage.setRGB(x + 1, y + 1, color)
            bufferedImage.setRGB(x, y + 1, color)
        } else if (x == 0) {
            var oldY = tabPom[1]
            var newy = oldY + 2
            bufferedImage.setRGB(x, newy, color)
            bufferedImage.setRGB(x + 1, newy, color)
            bufferedImage.setRGB(x + 1, newy + 1, color)
            bufferedImage.setRGB(x, newy + 1, color)
            tabPom.set(1, newy)
        } else if (y == 0) {
            var oldX = tabPom[0]
            var newx = oldX + 2
            bufferedImage.setRGB(newx, y, color)
            bufferedImage.setRGB(newx + 1, y, color)
            bufferedImage.setRGB(newx + 1, y + 1, color)
            bufferedImage.setRGB(newx, y + 1, color)
            tabPom.set(0, newx)
        }
        else if (tabPom[3] < netting.height-1) {
            var newy = tabPom[2] + 2 //srodkowy y - x pozostaje nie zmienny bo iterujemy w dol
            tabPom[3] = tabPom[3] + 1
            bufferedImage.setRGB(tabPom[0], newy, color)
            bufferedImage.setRGB(tabPom[0] + 1, newy, color)
            bufferedImage.setRGB(tabPom[0] + 1, newy + 1, color)
            bufferedImage.setRGB(tabPom[0], newy + 1, color)
            tabPom.set(2,newy)
        }
        if (tabPom[3] == netting.height-1) {
            tabPom.set(3, 0)
            tabPom.set(2,0)
        }
        return tabPom
    }
}