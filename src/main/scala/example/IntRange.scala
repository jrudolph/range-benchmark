/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2013, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala
package collection
package immutable

import scala.collection.mutable
import scala.annotation.tailrec

final class IntRange private (start: Int, end: Int, step: Int, numElements: Int)
    extends scala.collection.AbstractSeq[Int]
    with IndexedSeq[Int]
    with scala.collection.IndexedSeqOptimized[Int, IndexedSeq[Int]]
    with Serializable {
  // Console.err.println(s"new IntRange($start, $end, $step, $numElements) == $this")
  require(isEmpty || step * hops == end - start, this)

  override protected[this] def newBuilder: mutable.Builder[Int, IndexedSeq[Int]] = IndexedSeq.newBuilder[Int]

  override def head = start
  override def last = end
  override def length = numElements
  override def apply(idx: Int): Int = (
    if (0 <= idx && idx < numElements)
      start + step * idx
    else
      outOfRange(idx))
  override def max[A1 >: Int](implicit ord: Ordering[A1]): Int = if (ord eq Ordering.Int) max0 else super.max(ord)
  override def min[A1 >: Int](implicit ord: Ordering[A1]): Int = if (ord eq Ordering.Int) min0 else super.min(ord)

  @inline final override def foreach[@specialized(Unit) U](f: Int ⇒ U): Unit = {
    @tailrec def loop(n: Int) {
      f(n)
      if (n != end)
        loop(n + step)
    }
    if (nonEmpty)
      loop(start)
  }

  override def reverse: IntRange = new IntRange(end, start, -step, numElements)
  def contains(elem: Int) = (
    !isEmpty
    && min0 <= elem && elem <= max0
    && (elem - min0) % step == 0)
  def toRange = start to end by step

  private def outOfRange(idx: Int) = throw new IndexOutOfBoundsException(idx.toString)
  private def min0 = if (step > 0) start else end
  private def max0 = if (step > 0) end else start
  private def hops = if (isEmpty) 0 else numElements - 1

  override def equals(other: Any) = other match {
    case x: IntRange ⇒ if (isEmpty) x.isEmpty else length == x.length && head == x.head && last == x.last
    case _           ⇒ super.equals(other)
  }
  override def toString = s"$head to $last by $step"
}

object IntRange {
  val Empty = new IntRange(0, 0, 0, 0)

  def to(start: Int, end: Int): IntRangeBuilder = new IntRangeBuilder(start, end, inclusive = true)
  def until(start: Int, end: Int): IntRangeBuilder = new IntRangeBuilder(start, end, inclusive = false)
  def apply(start: Int, end: Int, step: Int, inclusive: Boolean = true): IntRange = {
    if (step == 0)
      throw new IllegalArgumentException("step cannot be 0.")

    val isEmpty = (
      (start > end && step > 0)
      || (start < end && step < 0)
      || (start == end && !inclusive))
    if (isEmpty)
      return Empty

    val gap = end.toLong - start.toLong
    val isExact = gap % step == 0
    val hasStub = inclusive || !isExact
    val lenLong = gap / step + (if (hasStub) 1 else 0)
    if (lenLong > scala.Int.MaxValue)
      throw new IllegalArgumentException("IntRange cannot contain more than Int.MaxValue elements.")

    val len = lenLong.toInt
    val last = start + step * (len - 1)

    new IntRange(start, last, step, len)
    //show(s"IntRange.apply($start, $end, $step, $inclusive)")(new IntRange(start, last, step, len))
  }
  private def show[T](msg: String)(x: T): T = { Console.err.println(s"$msg $x"); x }
}

class IntRangeBuilder(start: Int, end: Int, inclusive: Boolean) {
  def by(step: Int): IntRange = IntRange(start, end, step, inclusive)
}
object IntRangeBuilder {
  implicit def intRangeBuilderToIntRange(builder: IntRangeBuilder): IntRange = builder by 1
}
