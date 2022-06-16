package pl.edu.pja.s15165.cw6
package data.db

import akka.actor.ActorSystem
import com.scalapenos.riak.RiakClient

object Riak {
    private val HOST = "localhost"
    private val PORT = 8098

    def client(system: ActorSystem): RiakClient =
        RiakClient(system, HOST, PORT)
}
