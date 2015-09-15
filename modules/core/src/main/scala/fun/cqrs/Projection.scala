package fun.cqrs

import scala.concurrent.{ExecutionContext, Future}

trait Projection {

  def receiveEvent: HandleEvent

  final def onEvent(evt: DomainEvent): Future[Unit] = {
    if (receiveEvent.isDefinedAt(evt)) {
      receiveEvent(evt)
    } else {
      Future.successful(())
    }
  }

}
