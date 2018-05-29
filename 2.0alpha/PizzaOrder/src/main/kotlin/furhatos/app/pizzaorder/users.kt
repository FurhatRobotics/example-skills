package furhatos.app.pizzaorder

import furhatos.app.pizzaorder.nlu.OrderPizza
import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.Record
import furhatos.records.User

val User.order by NullSafeUserDataDelegate { OrderPizza() }