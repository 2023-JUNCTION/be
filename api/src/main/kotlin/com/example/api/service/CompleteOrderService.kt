package com.example.api.service

import com.example.api.client.SolumClient
import com.example.domain.repository.EslOrderRepository
import com.example.domain.repository.OrderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CompleteOrderService(
    val orderRepository: OrderRepository,
    val eslOrderRepository: EslOrderRepository,
    val solumClient: SolumClient,
) {
    @Transactional
    fun completeOrder(eslOrderNumber: Int) {
        val order = orderRepository.findFirstByDoneFalseAndEslOrderNumberOrderByCreateAtDesc(eslOrderNumber)
            ?: throw Exception("주문이 존재하지 않습니다.")

        // eslOrderNumber는 4, 3, 2, 1, 0번 순서
        val orders = orderRepository.findAllByDoneFalse()

        val imagesQueue: ArrayDeque<String> = ArrayDeque()

        for (i in orders.indices) {
            if (orders[i].eslOrderNumber != eslOrderNumber) {
                imagesQueue.addLast(orders[i].eslImage)
            }
        }

        val eslOrders = eslOrderRepository.findAllByOrderByOrderNumberDesc()

        for (i in 0 until 5) {
            if (imagesQueue.size == 0) {
                solumClient.pushLabelImage(eslOrders[i].labelCode, "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")
                continue
            }

            // 큐에서 뺀다
            val eslImage = imagesQueue.removeFirst()

            solumClient.pushLabelImage(eslOrders[i].labelCode, eslImage)
        }

        orders.forEach {
            if (it.eslOrderNumber < eslOrderNumber) {
                it.moveForward()
            } else if (it.eslOrderNumber == eslOrderNumber) {
                it.eslOrderNumber = -1
            }
        }

        order.complete()
        orderRepository.save(order)
        orderRepository.saveAll(orders)
    }

//    @Transactional
//    fun completeOrder(eslOrderNumber: Int) {
//        val order = orderRepository.findFirstByDoneFalseAndEslOrderNumberOrderByCreateAtDesc(eslOrderNumber)
//            ?: throw Exception("주문이 존재하지 않습니다.")
//
//        order.complete()
//
//        // eslOrderNumber는 4, 3, 2, 1, 0 번 순서
//        val orders = orderRepository.findAllByDoneFalse()
//
//        // stack에 넣음 eslOrderNumber는 제외
//        val imagesStack = orders.filter { it.eslOrderNumber != eslOrderNumber }.map { it.eslImage }.toMutableList()
//
//        orders.forEach {
//            if (it.eslOrderNumber <= 4) it.moveForward()
//        }
//
//        val eslOrders = eslOrderRepository.findAllByOrderByOrderNumberDesc()
//
//        for (i in  ) {
//
//        }
//
//        for (i in eslOrderNumber downTo 1) {
//            val eslOrdersIndex = eslOrderNumber - i
//            val imagesIndex = eslOrderNumber - i
//
//            val image = if (imagesIndex >= imagesStack.size) {
//                "iVBORw0KGgoAAAANSUhEUgAAAPoAAAB6CAYAAACWXE7lAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAhGVYSWZNTQAqAAAACAAFARIAAwAAAAEAAQAAARoABQAAAAEAAABKARsABQAAAAEAAABSASgAAwAAAAEAAgAAh2kABAAAAAEAAABaAAAAAAAAAEgAAAABAAAASAAAAAEAA6ABAAMAAAABAAEAAKACAAQAAAABAAAA+qADAAQAAAABAAAAegAAAADvqDCiAAAACXBIWXMAAAsTAAALEwEAmpwYAAABWWlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iWE1QIENvcmUgNi4wLjAiPgogICA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPgogICAgICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIgogICAgICAgICAgICB4bWxuczp0aWZmPSJodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyI+CiAgICAgICAgIDx0aWZmOk9yaWVudGF0aW9uPjE8L3RpZmY6T3JpZW50YXRpb24+CiAgICAgIDwvcmRmOkRlc2NyaXB0aW9uPgogICA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgoZXuEHAAALYUlEQVR4Ae2dMW/UShRGvU+vhfwAQPRE9KBQg6AGkTpR+iBSg0INghoEPYIeQY+gR9Aj4AcAP+C9d4xu4hivx5v1C87MuVK09nqzO3PGn2fmzp2Z2T//WaVJQAJZE/gr69yZOQlIoCag0L0RJFAAAYVeQCGbRQkodO8BCRRAQKEXUMhmUQLZCf3Ro0fVixcvLFkJSKBBIDuhnzhxotrZ2anW1tYUfKOgPSybwCzHcfSvX79WW1tb1adPn/ZK9/Pnz3vHHkigNAJ/55ZhxL27u1uL/MKFC9WNGzdyy6L5kcDCBLIT+tOnT2sIz58/rxC6JgEJVFV2Qj99+nRdiytyb28J7BPITuj0zz9+/Fgh+Ka1z5vXPJZA7gSyc8bdvn27evny5W/lpjPuNyS+URCB7Gp0ym5jY6O6fPlyQcVoViXQTyA7odNEv3jxoo64/nL3amEEsguYuXXrVt1HP3/+fHX27NmKpjxDbjdv3iysaM2uBPYJZFejE/567969ant7uzp58mT17Nmzus9+7ty5/Vx7JIHCCGTnjKPmPnPmTHX//v26KN+8eVNHyX348KEWfmHla3YlUBPIrulOH/3Lly97xRvH1O6aBEolkF3T/fr169X6+nrdJ//x40fdP6ffrkmgZALZNd0pTIJmmK7Kupd44BG/JoGSCWQp9JIL1LxLoItAdk33rkz6ngRyIDAv6pO8pSI/FXoOd4B5KIIAEZ90QwnxxrnMxC2Oh8zjsOlexC1iJnMiQCDY27dva4HjcGY1JYaP+yy74bW+zHpNAjkQoDanJsfpzPoLiD1l1ugpQl6XwMQIIG6iP8MYPiYStM8Ueh8dr0lgogTev39fEfVJn31IeLdCn2hBmiwJzCPw8OHDOk4krt+9e7eemh3nXa/20buo+J4EJkyAiVqImyG1Bw8eVAg/ZQo9RcjrEpgYAZxvEe3JENsQZ5zj6BMrRJMjgRQBxH316tVa7PTThyyEah89RdXrEpgYgZjLwcxMHHGbm5vJoBmb7hMrRJMjgRQBxtHZegwb0mznc9boUNAkcIwIsLgKAmcB1AiBZcOSPlPofXS8JoEJEmiHwLI+YmpSi033CRakSZJAHwGa7u/evas/8vr160FLpFmj9xH1mgQmSOAwIbAOr02wIE2SBLoIsMLxlStX6hr81atX9dqILIRqCGwXLd+TwDElwHRUHHARGReed7KT2h7cpvsxLXSTXR4BanREzoYk7cUmmJ/eZwq9j47XJDBBAiwpRaw7TjmMAJq28NvJ1uveJuK5BCZOgLHzCJRhi3Ca9CnTGZci5HUJTIQAc9BjD8GmuP+IM45mBH8RaE9/gqdOylkwEZYmQwKTJUAtjpZ2dnYqVpU5depUtbKycvRed544zI1F6MyTxRjzY4ZNKnJnsnRNmAQmRgB9ETCzurpa641NSlghts9GdcYRmtdlNC0Y99MkIIHlCdBsp0bHC884OqJPed1H7aOzQB21N0+ceMLgGbTZvnzh+g0SaBKga0zrmcksxLqnbNQanR+jD/Hz58/6lT4FzYror6cS43UJSCBNAIccvi+i5NhfcDab7W0TPu+/Rxc6tTmrX4T7nx8eshztvAT6vgQkcJAAGmPRCfroVKxUpikbXeisN40DjmY8NTkeQhLGThIxwJ9KlNclIIH5BJpDa/GpVB/9fwmYIUqHWrzpDVTkUSS+SmA5Aji3Q2Mc/5Fx9BjUxxlHAtinHMHHqpUIfkjClkPhf0sgXwLU6E+ePKl1RBeZ89Tea6N63UGLyx8j+D6M9+J9BB9j7HHdVwlIYDgBxE2FSoWJrpr+sHnfMnofnTG9b9++zfu9usmhF34uHi9IIEmgvVPLEGf3aELf2tqqm+jMkeVp0zZr8TYRzyWwGAFqbhzb1OTheSdgJjVzjV8ZremOuEkIP8rsmrYp9DYRzyWwGAGC0WgxoyXWihsqcn5lNKET4hqedZoSPHEQfbwuliU/LQEJ9BEgYIYJLUNttOE1RI3QqdVpxuMkIGqHKJ6upvzQBPo5CUhgnwBaYuEJXtEYx/ylbDShxw/x4zxtiNrhicMDYHd3Ny77KgEJHJIAWgpHNq+cD7XRmu7xg9FUj0ktvBItR00fTfv4rK8SkMBwAoibP7REH30RG13oPGUQO80JjmPLGEW+SLH4WQnMJxBzSZqaSs0QHW14LZLF04aEIHaMxOAlZJlaTQISWJ5Ac0umod82eo2OsPHAM7ElFpc35HVocfg5CaQJRD89/cn9T4zujKMmp0Ynxp2pdBx3javvJ8EjCUhgEQJojPj25l/q/0ev0Ylxp/kek1jop+N1j/NUgrwuAQn0EyBOZVEbvUb//v17HaIXzgEWmkf4/GkSkMDyBGi6M4xNzDuvQ5ryowudGpzBfProjKeTGN5regiXz6rfIIFyCRCQhlGzo6shATP/i9c91rQiMXrdoaBJYDwCTa87LWUWh0wtpz56H51aPPoQ1OT82Wwfr5D9JglQeTLBBb8XTXc0lrJRa3ScbrHgBD/OUrQkhPdSK2CkEup1CUjgFwE0xVqMGKLHD5Zydo8qdJoUOAYIjmF4DaM25z1Er0lAAocnQGs55qKjqzhH7KlafbSmO2PmGN52ni5Rs7O21RCv4OGz739KIH8CCJuYFILReG1bqo8+Wo2O0C9dulSLmqcLfQiePvGkceGJdtF4LoHFCDCRhU0bCEBjg8Uwlm47sqZ7CD1+vP2aeuK0P++5BCTQTaDpdWcDh2vXriW97qPV6CQpJrJ0JS9q9q5rvicBCaQJEJ8S+6M3P03LObWJ6ahCb/64xxKQwLgE6KfTJSZA5s6dO/XCLizGyr4JOOT6bPTIuL4f85oEJHB4AoiZvjjbL7GRKcd43nkApEyhpwh5XQITI0AILKvBYlHDp5Jo0z1FyOsSmBiBpjOO2nxICKw1+sQK0eRIIEWAJjw1Oc7vPxICm0qg1yUggeUJ/PEQ2OWz4DdIQAJDCFCb44hj2PpIQ2CHJM7PSEACyxNob7LIN6YC0nTGLc/db5DAkRLAGUdIeXMOSSogbbRJLUeaU39MAgUTQNTMEE0FyTQRKfQmDY8lcAwIEAlHKGxzr4RY7GVe8hX6PDK+L4GJEmACGbU5se9DTaEPJeXnJDARAqkpqV3J1BnXRcX3JDBhAjjj2pbyulujt4l5LoGJE2iKmvH0WLatL9mGwPbR8ZoEjgEBIuVSZo2eIuR1CUyMQHPxiYiOSyUxO6Ezm4ddYtrGkEQzwKB93XMJHBcCzfuYIbbY/qwv/dkJnb3fos/SjBZiOKIJqA+K1yQwRQIMp7Wb6dzXVG6p4Jns+ujsyR7hgQBA3Cxwv7m5OcWyM00SWIoAzriu5Z/bX5r18BoQWI2Dfsz29vbeVlFtCJ5L4LgSGLoKbHZNdwos+umxiQQiZz1sTQI5ESBCbn19/UAo7Lz8ZSf05vryNNujX474NQnkRGBlZaXuljZj3uflLzuhk9EQN8cRD0zfXZNATgRwwA0Nh81O6CyDu7q6Wq97jYeSJjtb2aS8kjndAOZFAm0C2XndaaIjbIytZTmnVsdpoUmgVALZCZ2CRNwst4PhkMPrrkmgZALZNd3ZooZmOjtOEjATtbtN95Jvc/OendBns1ndL2dvKk0CEvhFILume/TRCZZp/vG+JoFSCWRXo1OQCHxtbe1AmRoZdwCHJ4URyFLo9McfP358oCgdRz+Aw5PCCGQndJxxRAoxLVWTgAR+EchO6ATMMG7ebrpvbGw4g827vlgC2QmdIbWusECi5TQJlEog62mqpRaq+ZZAm0B2w2vtDHouAQlUlUL3LpBAAQQUegGFbBYloNC9ByRQAAGFXkAhm0UJKHTvAQkUQEChF1DIZlECCt17QAIFEFDoBRSyWZTAv02YjRywa38PAAAAAElFTkSuQmCC"
//            } else {
//                imagesStack[imagesIndex]
//            }
//
//            solumClient.pushLabelImage(eslOrders[eslOrdersIndex].labelCode, image)
//        }
//
//        orderRepository.saveAll(orders)
//    }
}
