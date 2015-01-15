package se.jaklec

import se.jaklec.api.Api
import se.jaklec.core.{CoreActors, BootedCore}

object Main extends App with BootedCore with CoreActors with Api
