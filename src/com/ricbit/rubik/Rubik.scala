package com.ricbit.rubik

trait Rubik {
  sealed abstract class Color
  case object Red extends Color
  case object Blue extends Color
  case object Green extends Color
  case object Yellow extends Color
  case object Orange extends Color
  case object White extends Color

  class Face(val colors: Vector[Vector[Color]]) { 
	val y = colors.length
    val x = colors.head.length
    require(colors.forall(_.length == x))
			  
    override def toString() = colors mkString "\n"
    
    def rotateClockwise(): Face = 
      new Face(colors.transpose map (_.reverse))
	
	def ==(that: Face) = this.colors == that.colors
  }
  
  class Cube(front: Face, back: Face,
             left: Face, right: Face,
             up: Face, down: Face) {
  }
  
}