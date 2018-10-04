package io.rybalkinsd.kotlinbootcamp.geometry

/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Collider): Boolean
}

/**
 * 2D point with integer coordinates
 */
data class Point(val x: Int, val y: Int) : Collider {
    override fun isColliding(other: Collider): Boolean {
        return when (other) {
            is Point -> other == this
            is Bar -> other.isColliding(this)
            else -> false
        }
    }
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
data class Bar(var firstCornerX: Int, var firstCornerY: Int, var secondCornerX: Int, var secondCornerY: Int) : Collider {
    val botCorX=minOf(firstCornerX, secondCornerX )
    val botCorY=minOf(firstCornerY, secondCornerY )
    val topCorX=maxOf(firstCornerY, secondCornerY )
    val topCorY=maxOf(firstCornerY, secondCornerY )


    init {
        this.firstCornerX = botCorX
        this.firstCornerY = botCorY
        this.secondCornerX = topCorX
        this.secondCornerY = topCorY
    }



    override fun isColliding(other: Collider): Boolean {
        return when (other) {
            is Point -> this.pointBar(other.x, other.y)
            is Bar -> this.barBar(other.botCorX, other.botCorY, other.topCorX, other.topCorY  )
            else -> false
        }
    }
    private fun pointBar(x: Int, y:Int ): Boolean =
            (botCorX <= x) && ( x<= topCorX) && (botCorY <= y) && ( y<= topCorY)

    private fun barBar(xb: Int, yb:Int, xt: Int, yt: Int ): Boolean {
        val xmaxb = maxOf(botCorX,xb)
        val xmaxt = maxOf(topCorX,xt)
        val xminb = minOf(botCorX,xb)
        val xmint = minOf(topCorX,xt)
        val ymaxb = maxOf(botCorY,yb)
        val ymaxt = maxOf(topCorY,yt)
        val yminb = minOf(botCorY,yb)
        val ymint = minOf(topCorY,yt)
        var s1: Boolean = (xminb<=xmaxb) && (xmaxb<=xmint) && (ymaxb<=ymint) && (ymint<=ymaxt)
        var s2: Boolean = (xminb<=xmaxb) && (xmaxb<=xmaxt) && (ymaxb<=ymint) && (ymint<=ymaxt)
        var s3: Boolean = (xminb<=xmaxb) && (xmaxb<=xmaxt) && (ymaxb<=ymint) && (ymint<=ymaxt)
        var s4: Boolean = (xminb<=xmaxb) && (xmaxb<=xmint) && (ymaxb<=ymint) && (ymint<=ymaxt)
        return (s1 || s2 || s3 || s4 )
    }

}

