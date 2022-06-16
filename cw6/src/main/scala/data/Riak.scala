package pl.edu.pja.s15165.cw6
package data

import com.scalapenos.riak.RiakClient

object Riak {
    private val HOST = "localhost"
    private val PORT = 8089
    private val CLIENT = RiakClient(HOST, PORT)

    def client: RiakClient = CLIENT
}
