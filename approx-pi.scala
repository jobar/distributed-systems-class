
import scala.math._

def apppi(t: (Double, Long, Long)): Double =
    (t._2 to t._3).foldLeft(0.0)((s: Double, k: Long) =>
        s + ((1/t._1) * sqrt((1 - pow((k / t._1), 2)))))


val i = pow(10, 12)
val n = pow(10, 5).toLong
val uow = (i / n).toLong

val inputs = (0L to n-1).map(k => {
    (i, 1 + (k * uow), (k + 1) * uow)
})

val parinputs = sc.parallelize(inputs, 1000)

val pi = 4 * parinputs.map(apppi).reduce(_ + _)


