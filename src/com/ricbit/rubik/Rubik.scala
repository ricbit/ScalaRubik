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
    require(colors forall (_.length == x))
			  
    override def toString() = colors mkString "\n"
    
    def rotateClockwise(): Face = 
      new Face(colors.transpose map (_.reverse))
    
    def takeSliceFrom(that: Face, slice: Int): Face = {
      def choose(i: Int): Vector[Color] = (if (slice != i) this else that).colors(i)
      new Face(((0 until y) map choose).toVector)
    }
	
    def ==(that: Face) = this.colors == that.colors
  }
  
  class Cube(val front: Face, val back: Face,
             val left: Face, val right: Face,
             val up: Face, val down: Face) {
    val y = front.y
    val x = front.x
    val z = left.y
    val faces = List[Cube => Face](_.front, _.back, _.left, _.right, _.up, _.down)
    
    def rotateClockwise(slice: Int): Cube = {
      new Cube(
          if (slice == 0) front.rotateClockwise else front,
          if (slice == z - 1) back.rotateClockwise else back,
          left.takeSliceFrom(down, slice),
          right.takeSliceFrom(up, slice),
          up.takeSliceFrom(left, slice),
          down.takeSliceFrom(right, slice)
      )
    }
        
    def ==(that:Cube) = 
      faces forall (x => x(this) == x(that)) 
      
    override def toString = 
      faces map (x => x(this)) mkString "---\n" 
  }
  
}