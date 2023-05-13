package org.example.domain.util;

import org.example.domain.model.ActorModel;

public interface Move {
    void goUp(ActorModel actorModel);
    void goRight(ActorModel actorModel);
    void goDown(ActorModel actorModel);
    void goLeft(ActorModel actorModel);
}
