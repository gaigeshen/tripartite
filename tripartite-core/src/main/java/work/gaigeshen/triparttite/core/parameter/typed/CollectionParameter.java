package work.gaigeshen.triparttite.core.parameter.typed;

import java.util.Collection;

/**
 *
 * @author gaigeshen
 */
public class CollectionParameter extends AbstractParameter<Collection<?>> {

  public CollectionParameter(String name, Collection<?> value) {
    super(name, value);
  }
}
