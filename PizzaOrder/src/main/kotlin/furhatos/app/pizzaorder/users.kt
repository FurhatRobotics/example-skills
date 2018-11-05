package furhatos.app.pizzaorder

import furhatos.app.pizzaorder.nlu.OrderPizzaIntent
import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

// Associate an order to a user
val User.order by NullSafeUserDataDelegate { OrderPizzaIntent() }