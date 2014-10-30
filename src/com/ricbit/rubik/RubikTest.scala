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
                 Vector(Blue, Yellow, White),
                 Vector(Orange, Red, White),
                 Vector(Red, Orange, Red))), 
               new Face(Vector(
                 Vector(Orange, White, Blue),
                 Vector(Red, Orange, Red),
                 Vector(Orange, White, Yellow))),
               new Face(Vector(
                 Vector(White, Yellow, Yellow),
                 Vector(Green, White, Green),
                 Vector(Blue, Red, White))),
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
  
  test("Move a slice from Face to Face") {
    new RubikTest {
      val first = new Face(Vector(
          Vector(Red, Blue, Yellow),
          Vector(Orange, Red, White),
          Vector(Blue, Yellow, Red)))
      val second = new Face(Vector(
          Vector(Blue, Orange, Red),
          Vector(Yellow, Red, Blue),
          Vector(Red, White, Yellow)))
      val expected = new Face(Vector(
          Vector(Red, Blue, Yellow),
          Vector(Yellow, Red, Blue),
          Vector(Blue, Yellow, Red)))
      assert(first.takeSliceFrom(second, 1) == expected)
    }
  }

  test("Rotate a cube") {
    new RubikTest {
      val expected = new Cube(new Face(Vector(
                 Vector(Orange, Green, Red),
                 Vector(Orange, Blue, Blue),
                 Vector(Green, Blue, Red))),
               new Face(Vector(
                 Vector(Orange, Yellow, Green),
                 Vector(Red, Green, White),
                 Vector(Green, Yellow, Green))),
               new Face(Vector(
                 Vector(Blue, Blue, White),
                 Vector(Orange, Red, White),
                 Vector(Red, Orange, Red))), 
               new Face(Vector(
                 Vector(White, Yellow, Yellow),
                 Vector(Red, Orange, Red),
                 Vector(Orange, White, Yellow))),
               new Face(Vector(
                 Vector(Blue, Yellow, White),
                 Vector(Green, White, Green),
                 Vector(Blue, Red, White))),
               new Face(Vector(
                 Vector(Orange, White, Blue),
                 Vector(Green, Yellow, Blue),
                 Vector(Yellow, Orange, Yellow))))      
      assert(cube.rotateClockwise(0) == expected)
    }
  }
  
}