# Smzr
[![<ORG_NAME>](https://circleci.com/gh/gutierreztineo/RebroteSolution.svg?style=shield)](<LINK>)

To start your Phoenix server:

  * Install dependencies with `mix deps.get`
  * Create and migrate your database with `mix ecto.setup`
  * Start Phoenix endpoint with `mix phx.server`

Now you can visit [`localhost:4000`](http://localhost:4000) from your browser.

Ready to run in production? Please [check our deployment guides](https://hexdocs.pm/phoenix/deployment.html).

## Learn more

  * Official website: https://www.phoenixframework.org/
  * Guides: https://hexdocs.pm/phoenix/overview.html
  * Docs: https://hexdocs.pm/phoenix
  * Forum: https://elixirforum.com/c/phoenix-forum
  * Source: https://github.com/phoenixframework/phoenix



## Config

Backend linked

`ln -s ~/RebroteSolution/Desarrollo/SMZR/Fuentes/Backend/smzr ~/smzr`

Grant BEAM

`sudo setcap 'cap_net_bind_service=+ep'  /usr/lib/erlang/erts-11.0.3/bin/beam.smp`
`sudo setcap 'cap_net_bind_service=+ep' _build/prod/rel/smzr/erts-11.0.2/bin/beam.smp`
