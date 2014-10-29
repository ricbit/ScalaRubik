package com.ricbit.rubik

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RubikSuite extends FunSuite {
  
  trait RubikTest extends Rubik {
      val cube = new Cube(new Face(Vector(
                 Vector(Red, Blue, Red),
                 Vector(Green, Blue, Blue),
                 Vector(Orange, Orange, Green))),
               new Face(Vector(
                 Vector(Orange, Yellow, Green),
                 Vector(Red, Green, White),
                 Vector(Green, Yellow, Green))),
               new Face(Vector(
                 Vector(Red, Orange, Blue),
                 Vector(Orange, Red, Yellow),
                 Vector(Red, White, White))),
               new Face(Vector(
                 Vector(Blue, Red, Yellow),
                 Vector(White, Orange, White),
                 Vector(Orange, Red, Orange))),
               new Face(Vector(
                 Vector(White, Red, Blue),
                 Vector(Green, White, Green),
                 Vector(Yellow, Yellow, White))),
               new Face(Vector(
                 Vector(Blue, Blue, White),
                 Vector(Green, Yellow, Blue),
                 Vector(Yellow, Orange, Yellow))))    
  }
  
  test("Build a Face") {
    new RubikTest {
      // This should build ok.
      val ok = new Face(Vector(
          Vector(Red, Blue, Yellow),
          Vector(Orange, Red, White),
          Vector(Blue, Yellow, Red)))
      // This should throw.
      intercept[IllegalArgumentException] {
  	    val fail = new Face(Vector(
    	  Vector(Red, Blue, Yellow),
    	  Vector(Orange, Red, White),
    	  Vector(Blue, Yellow, Red, Yellow)))
      }
    }
  }
  
  test("Rotate a 3x3 Face") {
    new RubikTest {
      val origin = new Face(Vector(
          Vector(Red, Blue, Yellow),
          Vector(Orange, Red, White),
          Vector(Blue, Yellow, Red)))
      val expected = new Face(Vector(
          Vector(Blue, Orange, Red),
          Vector(Yellow, Red, Blue),
          Vector(Red, White, Yellow)))
      assert(origin.rotateClockwise == expected)
    }
  }
  
  test("Rotate a 3x2 Face") {
    new RubikTest {
      val origin = new Face(Vector(
          Vector(Red, Blue, Yellow),
          Vector(Orange, Red, White)))
      val expected = new Face(Vector(
          Vector(Orange, Red),
          Vector(Red, Blue),
          Vector(White, Yellow)))
      assert(origin.rotateClockwise == expected)
    }
  }
  
  test("Rotate a cube") {
    new RubikTest {
    }
  }
  
}